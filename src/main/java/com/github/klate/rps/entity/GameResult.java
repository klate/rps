package com.github.klate.rps.entity;

import java.util.Date;
import javax.persistence.*;

/**
* class, that contains all the information of a played game between the server and a player
* */
@Entity
@Table(name = "GAME_RESULTS")
public class GameResult {

    // TODO: Use UUID
    // TODO: check if pre-generation of objects is possible -> reduces heap allocations
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private final char winner;
    private final String username;
    private final Date date;
    private final char playerChoice;
    private final char computerChoice;

    /**
     * required by JPI
     * empty constructor with dummy values
     * */
    protected GameResult() {
        //throw new IllegalStateException("");
        // TODO: check -> is the exception a good way of replacing the following 3 lines
        this.winner = this.playerChoice = this.computerChoice = ' ';
        this.date = null;
        this.username = null;
    }

    /**
     * creates a new instance, sets the current date and creates a new uuid
     *
     * @param winner the winner of the match
     * @param username the username of the player
     * @param playerChoice the choice in the game of the player
     * @param serverChoice the choice in the game of the server
     * */
    public GameResult(char winner, String username, char playerChoice, char serverChoice){
        this.date = new Date();
        this.winner = winner;
        this.username = username;
        this.playerChoice = playerChoice;
        this.computerChoice = serverChoice;
    }

    public char getWinner(){
        return this.winner;
    }

    public long getId(){
        return this.id;
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
