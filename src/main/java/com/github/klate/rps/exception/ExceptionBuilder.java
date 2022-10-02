package com.github.klate.rps.exception;

/**
 * class, that contains methods for building exceptions strings efficiently,
 * by minimizing the String-heap allocations
 * */
public class ExceptionBuilder {


    /**
     * creates an exception message from the given values
     * @param baseMessage the start of the message
     * @param value the appended value to the base message
     * */
    public static String createMessage(String baseMessage, char value){
        StringBuilder builder = new StringBuilder(baseMessage.length() + 1);
        builder.append(baseMessage).append(value);

        return builder.toString();
    }

    /**
     * creates an exception message from the given values
     * @param baseMessage the start of the message
     * @param value1 the first appended value to the base message
     * @param value2 the second appended value to the base message
     * */
    public static String createMessage(String baseMessage, char value1, char value2) {
        StringBuilder builder = new StringBuilder(baseMessage.length() + 3);
        builder.append(baseMessage)
                .append(value1)
                .append(' ')
                .append(value2);

        return builder.toString();
    }

    /**
     * creates an exception message from the given values
     * @param baseMessage the start of the message
     * @param params the first appended values to the base message (comma separated)
     * @param addOnMessage the second message to be appended towards the params
     * @param addOnParam the third message to be appended towards the end
     * */
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
