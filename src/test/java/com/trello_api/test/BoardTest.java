package com.trello_api.test;

import com.trello_api.entity.Board;
import com.trello_api.tools.ConstantVariables;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class BoardTest {

    Board board = new Board();

    @BeforeClass
    public static void setRequestSpecification(){
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(ConstantVariables.API_URL)
                .setBasePath(ConstantVariables.API_PATH)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .addQueryParam("key", ConstantVariables.KEY)
                .addQueryParam("token", ConstantVariables.TOKEN)
                .log(LogDetail.ALL)
                .build();
    }

    @Test(priority = 0)
    public void createBoard(){
        board =
        given()
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

    @Test(priority = 1)
    public void verifyBoardCreate(){
        given()
                .spec(requestSpecification)
        .when()
                .get(board.getId())
        .then()
                .statusCode(200)
                .body("name", containsString("test"));
    }

    @Test(priority = 2)
    public void deleteBoard(){
        given()
                .spec(requestSpecification)
        .when()
                .delete(board.getId())
        .then()
                .statusCode(200)
                .body("_value", equalTo(null));
    }
}
