package com.example.Atmproject;

import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static io.restassured.RestAssured.given;
import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

public class HoverflyTests {
    @Rule
    public static HoverflyRule hoverflyRule = HoverflyRule.inSimulationMode(dsl(
            service("localhost:8080/api")
            .get("/online")
            .willReturn(success().status(200))
    ));
    TestRestTemplate testRestTemplate = new TestRestTemplate();

    @Test
    public void testOnline() {
        given().when().get("localhost:8080/api/online").then().assertThat().statusCode(200);
    }
}
