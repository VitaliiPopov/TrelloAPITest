package com.trello_api.tools;

public final class ConstantVariables {

    private ConstantVariables(){
        throw new IllegalStateException("Utility class");
    }

    public static final String TOKEN = System.getenv("TOKEN");
    public static final String KEY = System.getenv("KEY");
    public static final String API_URL = "https://api.trello.com";
    public static final String API_PATH = "/1/boards/";
}
