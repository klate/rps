package com.github.klate.rps.models;

import java.util.Date;

// todo: check if a record makes more sense here

/**
* class, that contains all the information of a played game between the server and a player
* */
public class GameResult {

    // TODO: switch to uuid, that's not heap allocated (if possible)
    private final String uuid;
    private final char winner; // todo: maybe switch to enums
    private final String username;
    private final Date date;
    private final char playerChoice;
    private final char computerChoice;

    /**
     * creates a new instance, sets the current date and creates a new uuid
     *
     * @param winner the winner of the match
     * @param username the username of the player
     * @param playerChoice the choice in the game of the player
     * @param serverChoice the choice in the game of the server
     * */
    public GameResult(char winner, String username, char playerChoice, char serverChoice){
        this.date = new Date(); // todo -> maybe get as param
        this.uuid = "new uuid"; // todo -> create uuid here
        this.winner = winner;
        this.username = username;
        this.playerChoice = playerChoice;
        this.computerChoice = serverChoice;
    }

    public char getWinner(){
        return this.winner;
    }

    public String getUuid(){
        return this.uuid;
    }

    public String getUsername() {
        return this.username;
    }

    public Date getDate() {
        return this.date;
    }

    public char getPlayerChoice(){
        return this.playerChoice;
    }

    public char getComputerChoice() {
        return this.computerChoice;
    }
}
