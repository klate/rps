package com.github.klate.rps.globals;

/**
 * Class, that contains static globals for the Game Logic
 */
public class GameGlobals {

    /**
    * Char value for the game choice "rock"
    * */
    public static final char rock = 'r';

    /**
     * Char value for the game choice "paper"
     * */
    public static final char paper = 'p';

    /**
     * Char value for the game choice "scissors"
     * */
    public static final char scissors = 's';

    /**
     * Char-array, that contains all the valid options for a player to choose
     * */
    public static final char[] validChoices = new char[] { rock, paper, scissors };

    /**
     * Char value for the game result "draw"
     * */
    public static final char draw = 'd';

    /**
     * Char value for the game result "server won"
     * */
    public static final char serverWon = 's';

    /**
     * Char value for the game result "draw"
     * */
    public static final char playerWon = 'p';

}
