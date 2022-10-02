package com.github.klate.rps.exception;

public class ExceptionBuilder {


    /**
     * creates an exception message from the given values
     * minmizes the String-heap allocations while doing it
     * */
    public static String createMessage(String baseMessage, char value){
        StringBuilder builder = new StringBuilder(baseMessage.length() + 1);
        builder.append(baseMessage).append(value);

        return builder.toString();
    }

    public static String createMessage(String baseMessage, char value1, char value2) {
        StringBuilder builder = new StringBuilder(baseMessage.length() + 3);
        builder.append(baseMessage)
                .append(value1)
                .append(' ')
                .append(value2);

        return builder.toString();
    }

    public static String createMessage(String baseMessage, char[] params, String addOnMessage, char addOnParam){
        StringBuilder builder = new StringBuilder(baseMessage.length() + (params.length * 2) + addOnMessage.length() + 1);
        builder.append(baseMessage);

        for (int i = 0; i < params.length; i++) {
            builder.append(params[i]);
            if (i < params.length - 1){
                builder.append(',');
            }
        }

        builder.append(' ').append(addOnMessage).append(addOnParam);

        return builder.toString();
    }

}
