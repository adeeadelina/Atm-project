package com.example.Atmproject;

import com.example.Atmproject.dto.ATMResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static io.restassured.RestAssured.get;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanIntegrationTests {
    @Test
    public void withdraw300and455() {
        TreeMap<String, Integer> expectedTreeMap = new TreeMap<>();
        expectedTreeMap.put("ONEHUNDRED_RON(100)", 3);
        ATMResponseDTO actualResponse = get("/api/iau300").as(ATMResponseDTO.class);
        assertEquals("Transaction approved", actualResponse.getResponseMessage());
        assertEquals( expectedTreeMap, actualResponse.getBills());
        expectedTreeMap.clear();

        actualResponse = get("/api/cer455RON").as(ATMResponseDTO.class);
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
        ATMResponseDTO actualResponse = get("/api/vreausaretragnumai2lei,nambani").as(ATMResponseDTO.class);
        assertEquals("Transaction approved", actualResponse.getResponseMessage());
        assertEquals( expectedTreeMap, actualResponse.getBills());
        expectedTreeMap.clear();

        actualResponse = get("/api/extrage151bancomatule").as(ATMResponseDTO.class);
        expectedTreeMap.put("ONEHUNDRED_RON(100)", 1);
        expectedTreeMap.put("FIFTY_RON(50)", 1);
        expectedTreeMap.put("ONE_RON(1)", 1);
        assertEquals("Transaction approved", actualResponse.getResponseMessage());
        assertEquals( expectedTreeMap, actualResponse.getBills());
        expectedTreeMap.clear();

        actualResponse = get("/api/iau1477saunu?nustiu").as(ATMResponseDTO.class);
        expectedTreeMap.put("ONEHUNDRED_RON(100)", 14);
        expectedTreeMap.put("FIFTY_RON(50)", 1);
        expectedTreeMap.put("TEN_RON(10)", 2);
        expectedTreeMap.put("FIVE_RON(5)", 1);
        expectedTreeMap.put("ONE_RON(1)", 2);
        assertEquals("Transaction approved", actualResponse.getResponseMessage());
        assertEquals( expectedTreeMap, actualResponse.getBills());
        expectedTreeMap.clear();

        // no connection to other servers-> not enough money exception
        int statusCode = get("/api/potsaretrag7000lei").getStatusCode();
        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, statusCode);

    }

    @Test
    public void deposit200withdraw1477() {
        assertEquals(200, get("/api/puncam200lei").getStatusCode());
        TreeMap<String, Integer> expectedTreeMap = new TreeMap<>();
        ATMResponseDTO actualResponse = get("/api/retrag1477RON").as(ATMResponseDTO.class);
        expectedTreeMap.put("ONEHUNDRED_RON(100)", 14);
        expectedTreeMap.put("FIFTY_RON(50)", 1);
        expectedTreeMap.put("TEN_RON(10)", 2);
        expectedTreeMap.put("FIVE_RON(5)", 1);
        expectedTreeMap.put("ONE_RON(1)", 2);
        assertEquals("Transaction approved", actualResponse.getResponseMessage());
        assertEquals( expectedTreeMap, actualResponse.getBills());
        expectedTreeMap.clear();

    }

    @Test
    public void clearATMthenfillThenWithdraw() {
        String url = "/api/retrag" + get("/api/check-balance").asString().replaceAll("[^0-9]", "");
        ATMResponseDTO actualResponse = get(url).as(ATMResponseDTO.class);
        assertEquals("Transaction approved", actualResponse.getResponseMessage());
        assertEquals(200, get("/api/depun200RON").getStatusCode());
        actualResponse = get("/api/scot200RON").as(ATMResponseDTO.class);
        TreeMap<String, Integer> expectedTreeMap = new TreeMap<>();
        expectedTreeMap.put("ONEHUNDRED_RON(100)", 2);
        assertEquals("Transaction approved", actualResponse.getResponseMessage());
        assertEquals( expectedTreeMap, actualResponse.getBills());

    }
}
