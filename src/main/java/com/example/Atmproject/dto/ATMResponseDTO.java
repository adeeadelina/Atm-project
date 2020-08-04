package com.example.Atmproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class ATMResponseDTO {

    private TreeMap<String, Integer> bills;
    private String responseMessage;

    public ATMResponseDTO(String responseMessage, TreeMap<String, Integer> bills) {
        this.responseMessage = responseMessage;
        if (bills == null) {
            this.bills = new TreeMap<>();
        } else {
            this.bills = bills;
        }
    }

    public static TreeMap<String, Integer> transformBillsToString(TreeMap<Integer, Integer> billsToTransform) {
        TreeMap<String, Integer> billsToReturn = new TreeMap<>();
        for (Map.Entry<Integer, Integer> entryBill : billsToTransform.entrySet()) {
            billsToReturn.put(transformBillToString(entryBill.getKey()), entryBill.getValue());
        }
        return billsToReturn;
    }

    public static String transformBillToString(int billToTransform) {
        if (billToTransform == 1) {
            return "ONE_RON(1)";
        } else if (billToTransform == 5) {
            return "FIVE_RON(5)";
        } else if (billToTransform == 10) {
            return "TEN_RON(10)";
        } else if (billToTransform == 50) {
            return "FIFTY_RON(50)";
        } else {
            return "ONEHUNDRED_RON(100)";
        }
    }

    public void setBills(TreeMap<String, Integer> bills) {
        this.bills = bills;
    }

    @JsonProperty("responseMessage")
    public void setResponseMessage(String message) {
        responseMessage = message;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public TreeMap<String, Integer> getBills() {
        return bills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ATMResponseDTO that = (ATMResponseDTO) o;
        return responseMessage.equals(that.responseMessage) &&
                Objects.equals(bills, that.bills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseMessage, bills);
    }


}
