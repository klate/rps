package com.github.klate.rps.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

import static com.github.klate.rps.globals.ExceptionGlobals.ERROR_WRONG_USERNAME;


/**
* class, that contains all the information of a played game between the server and a player
* */
@Data
@Document(collection = "gameresults")
public class GameResult {

    @Id
    private String id;
    private char winner;
    private String userName;
    private Date date;
    private char playerChoice;
    private char serverChoice;

    /**
     * empty constructor with dummy values
     * */
    protected GameResult() {
        this.playerChoice = ' ';
        this.userName = null;
        this.date = null;
    }

    /**
     * creates a new instance, sets the current date and creates a new uuid
     *
     * @param userName the username of the player
     * @param playerChoice the choice in the game of the player
     * */
    public GameResult(String userName, char playerChoice) {
        if(userName != null && !userName.isEmpty() && !userName.isBlank()){
            this.date = new Date();
            this.userName = userName;
            this.playerChoice = playerChoice;
        } else {
            throw new IllegalArgumentException(ERROR_WRONG_USERNAME);
        }
    }

}
