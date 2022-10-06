package com.github.klate.rps.globals;

import com.github.klate.rps.util.ArrayUtils;

/**
 * Class, that contains static globals for the Game Logic
 */
public class GameGlobals {

    /**
     * Char-array, that contains all the valid options for a player to choose
     * the order of the elements defines what beats what. must be an uneven number > 2
     * */
    public static final char[] gameChoices = new char[] { 'r', 'p', 's' };

    /**
     * short int16 array, that contains the game values as the gameChoices array,
     * but cast to the equivalent short value
     * */
    public static final short[] gameChoicesShort = ArrayUtils.castArray(gameChoices);

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
