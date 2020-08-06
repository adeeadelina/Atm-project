package com.example.Atmproject;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class HoverflyTests {
////    @Rule
//    public static HoverflyRule hoverflyRule = HoverflyRule.inSimulationMode(dsl(
//            service("localhost:8080/api")
//            .get("/online")
//            .willReturn(success().status(200))
//    ));
//    TestRestTemplate testRestTemplate = new TestRestTemplate();

    @Test
    public void testOnline() {
        given().when().get("localhost:8080/api/online").then().assertThat().statusCode(200);
    }
}
