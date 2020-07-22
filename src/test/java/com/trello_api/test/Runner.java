package com.trello_api.test;

import com.trello_api.entity.Board;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.*;

public class Runner {

    protected Board board = new Board();
    private final String TOKEN = System.getenv("TOKEN");
    private final String KEY = System.getenv("KEY");

    @BeforeClass
    @Parameters({"url", "path"})
    public void setRequestSpecification(String url, String path){
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(url)
                .setBasePath(path)
                .setAccept(JSON)
                .setContentType(JSON)
                .addQueryParam("key", KEY)
                .addQueryParam("token", TOKEN)
                .log(ALL)
                .build();
    }
}