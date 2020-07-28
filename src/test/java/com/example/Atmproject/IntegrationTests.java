package com.example.Atmproject;

import com.example.Atmproject.dto.ATMResponseDTO;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTests {


    @Test
    public void idServerUp() {
        given().when().get("http://localhost:8080/api/online").then().statusCode(200);
    }
    @Test
    public void withdraw300and455() {
        TreeMap<String, Integer> expectedTreeMap = new TreeMap<>();
        expectedTreeMap.put("ONEHUNDRED_RON(100)", 3);
        ATMResponseDTO actualResponse = get("/api/new-transaction?sum=300").as(ATMResponseDTO.class);
        assertEquals("Transaction approved", actualResponse.getResponseMessage());
        assertEquals( expectedTreeMap, actualResponse.getBills());
        expectedTreeMap.clear();

        actualResponse = get("/api/new-transaction?sum=455").as(ATMResponseDTO.class);
        expectedTreeMap.put("ONEHUNDRED_RON(100)", 4);
        expectedTreeMap.put("FIFTY_RON(50)", 1);
        expectedTreeMap.put("FIVE_RON(5)", 1);
        assertEquals("Transaction approved", actualResponse.getResponseMessage());
        assertEquals( expectedTreeMap, actualResponse.getBills());
	}

	@Test
    public void withdraw2and151and1477and7000() {
        TreeMap<String, Integer> expectedTreeMap = new TreeMap<>();
        expectedTreeMap.put("ONE_RON(1)", 2);
        ATMResponseDTO actualResponse = get("/api/new-transaction?sum=2").as(ATMResponseDTO.class);
        assertEquals("Transaction approved", actualResponse.getResponseMessage());
        assertEquals( expectedTreeMap, actualResponse.getBills());
        expectedTreeMap.clear();

        actualResponse = get("/api/new-transaction?sum=151").as(ATMResponseDTO.class);
        expectedTreeMap.put("ONEHUNDRED_RON(100)", 1);
        expectedTreeMap.put("FIFTY_RON(50)", 1);
        expectedTreeMap.put("ONE_RON(1)", 1);
        assertEquals("Transaction approved", actualResponse.getResponseMessage());
        assertEquals( expectedTreeMap, actualResponse.getBills());
        expectedTreeMap.clear();

        actualResponse = get("/api/new-transaction?sum=1477").as(ATMResponseDTO.class);
        expectedTreeMap.put("ONEHUNDRED_RON(100)", 14);
        expectedTreeMap.put("FIFTY_RON(50)", 1);
        expectedTreeMap.put("TEN_RON(10)", 2);
        expectedTreeMap.put("FIVE_RON(5)", 1);
        expectedTreeMap.put("ONE_RON(1)", 2);
        assertEquals("Transaction approved", actualResponse.getResponseMessage());
        assertEquals( expectedTreeMap, actualResponse.getBills());
        expectedTreeMap.clear();

        actualResponse = get("/api/new-transaction?sum=7000").as(ATMResponseDTO.class);
        expectedTreeMap.put("ONEHUNDRED_RON(100)", 70);
        assertEquals("Transaction approved", actualResponse.getResponseMessage());
        assertEquals( expectedTreeMap, actualResponse.getBills());

    }
}
