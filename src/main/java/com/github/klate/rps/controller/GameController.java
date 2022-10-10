package com.github.klate.rps.controller;

import com.github.klate.rps.entity.GameResult;
import com.github.klate.rps.exception.ExceptionBuilder;
import com.github.klate.rps.exception.GlobalExceptionHandler;
import com.github.klate.rps.service.GameResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.InvalidParameterException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

import static com.github.klate.rps.globals.ExceptionGlobals.*;
import static com.github.klate.rps.globals.GameGlobals.*;
import static com.github.klate.rps.util.ArrayUtils.getIndexInArray;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;

/**
* Controller for the server-side game logic of the server
* exposes the game endpoint towards the users
* */
@RestController
@RequestMapping("/api")
public class GameController {

    // the logger for this class
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // the background service for handling the game results
    private final GameResultService gameResultService;

    /**
     * Constructs a new GameController
     *
     * @param gameResultService the service for accessing the game result data
     */
    public GameController(GameResultService gameResultService) {
        this.gameResultService = gameResultService;
    }

    /**
     * spring endpoint towards the user to play the game rock paper scissors
     *
     * @param username:    the name of the player playing
     * @param playerChoice the choice ([r]ock, [p]aper, [s]cissors)
     * @return GameResult-obj, that contains the information about the result of the game
     * @throws InvalidParameterException when the playerChoice is incorrect
     * @throws IllegalStateException     when an undefined state occurred
     */
    @GetMapping("/play")
    @Async
    @ResponseBody
    public CompletableFuture<GameResult> play(
            @RequestParam(value = "name") final String username,
            @RequestParam(value = "c") final Character playerChoice,
            HttpServletResponse response)
            throws InvalidParameterException, IllegalStateException {

        CompletableFuture<GameResult> gameResultCompletableFuture = CompletableFuture
                // 1. check the user input
                .supplyAsync(() -> checkChoiceInput(playerChoice))
                // 2. create the response obj
                .thenApply(pChoice -> new GameResult(username, pChoice))
                // 3. get server choice
                .thenApply((gameResult) -> {
                    gameResult.setServerChoice(GameController.getServerChoice());
                    return gameResult;
                })
                // 4. determine the winner
                .thenApply((gameResult) -> {
                    gameResult.setWinner(getWinner(gameResult.getPlayerChoice(), gameResult.getServerChoice()));
                    return gameResult;
                });

        // save game result -> don't wait for it
        this.saveGameResultAsync(gameResultCompletableFuture);

        // TODO: find generic way
        response.setHeader("Access-Control-Allow-Origin", "*");

        return gameResultCompletableFuture;
    }

    /**
     * checks if the given input is one of the valid choices
     *
     * @param userInput the user input choice to check
     * @throws InvalidParameterException when the input is not one of the valid choices
     */
    private static char checkChoiceInput(char userInput) throws InvalidParameterException {
        if (isUpperCase(userInput)) {
            userInput = toLowerCase(userInput);
        }

        for (final char validChoice : gameChoices) {
            if (validChoice == userInput) {
                return userInput;
            }
        }

        throw new InvalidParameterException(
                ExceptionBuilder.createMessage(ACCEPTED_INPUTS, gameChoices, SENT, userInput));
    }

    /**
     * returns a randomly generated server choice for the game
     *
     * @return the server choice
     */
    private static char getServerChoice() {
        return gameChoices[ThreadLocalRandom.current().nextInt(gameChoices.length)];
    }

    /**
     * saves the given game result future asynchronously
     *
     * @param gameResultFuture a future, that will contain the game result
     */
    private void saveGameResultAsync(CompletableFuture<GameResult> gameResultFuture) {
        CompletableFuture.runAsync(() -> {
            try {
                this.gameResultService.create(gameResultFuture.get());
            } catch (Exception gameSaveException) {
                logger.error(ERROR_SAVING_GAME_RESULT, gameSaveException);
            }
        });
    }

    /**
     * determines the winner of the game from the given input
     *
     * @param playerChoice the choice of the place
     * @param serverChoice the choice of the server
     */
    private static char getWinner(char playerChoice, char serverChoice) {
        if (playerChoice == serverChoice){
            return draw;
        }

        final int diff = getIndexInArray(gameChoices, playerChoice) - getIndexInArray(gameChoices, serverChoice);

        if(diff % 2 == 0) {
            // even
            if (diff > 0) {
                return serverWon;
            } else {
                return playerWon;
            }
        } else {
            // uneven
            if (diff > 0) {
                return playerWon;
            } else {
                return serverWon;
            }
        }
    }




}
