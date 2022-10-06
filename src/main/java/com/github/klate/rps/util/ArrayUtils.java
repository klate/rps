package com.github.klate.rps.util;

import jdk.incubator.vector.ShortVector;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.VectorSpecies;

import static com.github.klate.rps.globals.ExceptionGlobals.VALUE_NOT_FOUND_ARRAY;

public class ArrayUtils {

    private static final VectorSpecies<Short> VECTOR_SPECIES = ShortVector.SPECIES_PREFERRED;

    /**
     * returns the index of the given value inside the given array
     * @param array the array to search through
     * @param value the value to search for
     * */
    public static int getIndexInArray(char[] array, char value) throws Exception {
        for (int i = 0; i < array.length; i++) {
            if (value == array[i]) {
                return i;
            }
        }
        // TODO: switch to better exception type
        throw new Exception(VALUE_NOT_FOUND_ARRAY);
    }

    /**
     * casts the given char array into a short array. the order is unaffected
     * @param array the source array
     * @return a short array with the equivalent of the char array data
     * */
    public static short[] castArray(char[] array){
        final short[] shortArray = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            shortArray[i] = (short)array[i];
        }
        return shortArray;
    }

    /**
     * gets the index of the given short value inside the given array using the java vector api
     * @param array the array to search
     * @param value the value to search for
     * @return the index
     * */
    public static int getIndexInArrayVector(short[] array, short value) throws Exception {
        int i = 0;
        for(; i < VECTOR_SPECIES.loopBound(array.length); i+= VECTOR_SPECIES.length())
        {
            ShortVector vector = ShortVector.fromArray(VECTOR_SPECIES, array, i);
            VectorMask<Short> result = vector.eq(value);
            int vectorResultIndex = result.firstTrue();
            if(vectorResultIndex < VECTOR_SPECIES.length()){
                return i + vectorResultIndex;
            }
        }

        // process leftover data, that didn't fit inside the vector
        for (; i < array.length; i++) {
            if (value == array[i]) {
                return i;
            }
        }

        // TODO: switch to better exception type
        throw new Exception(VALUE_NOT_FOUND_ARRAY);
    }

}
