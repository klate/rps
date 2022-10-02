package com.github.klate.rps.controller;

import com.github.klate.rps.entity.GameResult;
import com.github.klate.rps.exception.ExceptionBuilder;
import com.github.klate.rps.globals.ExceptionGlobals;
import com.github.klate.rps.service.GameResultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

import static com.github.klate.rps.globals.ExceptionGlobals.*;
import static com.github.klate.rps.globals.GameGlobals.*;
import static java.lang.Character.*;

/**
* Controller for the server-side game logic of the server
* exposes the game endpoint towards the users
* */
@RestController
@RequestMapping("/api")
public class GameController {

    // the background service for handling the game results
    private final GameResultService gameResultService;

    public GameController(GameResultService gameResultService) {
        this.gameResultService = gameResultService;
    }

    /**
    * spring endpoint towards the user to play the game rock paper scissors
    * @param name: the name of the player playing
    * @param playerChoice the choice (rock, paper, scirros)
    *
    * @throws -> todo: check how spring handles this
    *
    * @return GameResult-obj, that contains the information about the result of the game
    * */
    @GetMapping("/play")
    public CompletableFuture<GameResult> play(
        @RequestParam(value = "name") String name, @RequestParam(value = "c") char playerChoice)
        throws InvalidParameterException, IllegalStateException {

        // TODO: make the game processing logic also async
        // non blocking for the main thread

        // execution order
        // 1. checkChoiceInput
        // 2. getServerChoice
        // 3. getWinner
        // 4. create game result obj
        // 5. saveGameResult -> do not wait -> async
        // return game result



        playerChoice = checkChoiceInput(playerChoice);

        final char serverChoice = getServerChoice();
        final char winner = getWinner(playerChoice, serverChoice);

        // TODO: add exceptoinhandling and translate them to HTTP Codes

        // todo: maybe to heap alloc of properties on other thread and then just use the objects here
        //  -> less heap allocations in response time
        final GameResult resultObj = new GameResult(winner, name, playerChoice, serverChoice);

        this.gameResultService.saveGameResult(resultObj);

        return CompletableFuture.completedFuture(resultObj);
    }

    /**
     * checks if the given input is one of the valid choices
     * @see validChoices valid choices
     *
     * @param userInput the user input choice to check
     * @throws InvalidParameterException when the input is not one of the valid choices
     * */
    private static char checkChoiceInput(char userInput) throws InvalidParameterException {
        if (isUpperCase(userInput)){
            userInput = toLowerCase(userInput);
        }

        for (char validChoice : validChoices) {
            if (validChoice == userInput) {
                return userInput;
            }
        }

        throw new InvalidParameterException(
                ExceptionBuilder.createMessage(ACCEPTED_INPUTS, validChoices, SENT, userInput));
    }

    /**
     * returns a randomly generated server choice for the game
     * @see validChoices available choices
     *
     * @return the server choice
     * */
    private static char getServerChoice(){
        // todo check for heap allocations on ThreadLocalRandom.current()
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0 -> {
                return rock;
            }
            case 1 -> {
                return paper;
            }
            case 2 -> {
                return scissors;
            }
            default -> throw new IllegalStateException(UNKNOWN_SERVER_CHOICE);
        }
    }

    /**
     * determines the winner of the game from the given input
     *
     * @see validChoices for the valid choices of player and server
     *
     * @param playerChoice the choice of the place
     * @param serverChoice the choice of the server
     *
     * @throws IllegalStateException for unexpected combinations
     * */
    private static char getWinner(char playerChoice, char serverChoice) throws IllegalStateException {
        if (playerChoice == serverChoice){
            return draw;
        }

        // TODO: check: is there a more elegant way?
        // -> if there is: make multiple versions of this method avalible via dependecy injection
        // -> configurable via v1/v2/vX api routes?
        // -> maybe with relations? -> rock ==> scissors ==> paper ==> rock;
        // from left to right is the winner -> check whoose left index is clear?


        switch (playerChoice) {
            case rock -> {
                switch (serverChoice) {
                    case paper -> {
                        return serverWon;
                    }
                    case scissors -> {
                        return playerWon;
                    }
                    default -> throw new IllegalStateException(
                            ExceptionBuilder.createMessage(UNEXPECTED_COMBINATION, playerChoice, serverChoice));
                }
            }
            case paper -> {
                switch (serverChoice) {
                    case scissors -> {
                        return serverWon;
                    }
                    case rock ->  {
                        return playerWon;
                    }
                    default -> throw new IllegalStateException(
                            ExceptionBuilder.createMessage(UNEXPECTED_COMBINATION, playerChoice, serverChoice));
                }
            }
            case scissors -> {
                switch (serverChoice){
                    case rock -> {
                        return serverWon;
                    }
                    case paper -> {
                        return playerWon;
                    }
                    default -> throw new IllegalStateException(
                            ExceptionBuilder.createMessage(UNEXPECTED_COMBINATION, playerChoice, serverChoice));
                }
            }
            default -> throw new IllegalStateException(
                    ExceptionBuilder.createMessage(UNEXPECTED_VALUES, playerChoice));
        }
    }




}
