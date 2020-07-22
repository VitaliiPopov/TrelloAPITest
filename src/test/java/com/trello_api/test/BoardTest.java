package com.trello_api.test;

import com.trello_api.entity.Board;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class BoardTest extends Runner {

    @Test(priority = 1)
    public void createBoard() {
        board = given()
                .spec(requestSpecification)
                .queryParam("name", "test")
        .when()
                .post()
        .then()
                .statusCode(200)
        .extract()
                .response()
                .as(Board.class);
    }

    @Test(priority = 2, dependsOnMethods = "createBoard")
    public void verifyBoardCreate() {
        given()
                .spec(requestSpecification)
        .when()
                .get(board.getId())
        .then()
                .statusCode(200)
                .body("name", containsString("test"));
    }

    @Test(priority = 3, dependsOnMethods = "createBoard")
    public void deleteBoard() {
        given()
                .spec(requestSpecification)
       .when()
                .delete(board.getId())
       .then()
                .statusCode(200)
                .body("_value", equalTo(null));
    }
}