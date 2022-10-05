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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private char winner;
    private final String userName;
    private final Date date;
    private final char playerChoice;
    private char serverChoice;

    /**
     * required by JPI
     * empty constructor with dummy values
     * */
    protected GameResult() {
        this.playerChoice = ' ';
        this.date = null;
        this.userName = null;
    }

    /**
     * creates a new instance, sets the current date and creates a new uuid
     *
     * @param userName the username of the player
     * @param playerChoice the choice in the game of the player
     * */
    public GameResult(String userName, char playerChoice) {
        this.date = new Date();
        this.userName = userName;
        this.playerChoice = playerChoice;
    }

    public char getWinner(){
        return this.winner;
    }

    public void setWinner(char winner){
        this.winner = winner;
    }

    public long getId(){
        return this.id;
    }

    public String getUserName() {
        return this.userName;
    }

    public Date getDate() {
        return this.date;
    }

    public char getPlayerChoice(){
        return this.playerChoice;
    }

    public char getServerChoice() {
        return this.serverChoice;
    }

    public void setServerChoice(char serverChoice){
        this.serverChoice = serverChoice;
    }
}
