package com.github.klate.rps.util;

import com.github.klate.rps.exception.ExceptionBuilder;

import java.security.InvalidParameterException;

import static com.github.klate.rps.globals.ExceptionGlobals.ACCEPTED_INPUTS;
import static com.github.klate.rps.globals.ExceptionGlobals.PROVIDED;

public class ArrayUtils {

    /**
     * returns the index of the given value inside the given array
     * @param array the array to search through
     * @param value the value to search for
     * */
    public static int getIndexInArray(char[] array, char value) {
        for (int i = 0; i < array.length; i++) {
            if (value == array[i]) {
                return i;
            }
        }
        throw new InvalidParameterException(
                ExceptionBuilder.createMessage(ACCEPTED_INPUTS, array, PROVIDED, value));
    }

}
