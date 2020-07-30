package com.example.Atmproject.dto;

import com.example.Atmproject.util.Status;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class ATMResponseDTO {

    private final TreeMap<String, Integer> bills;
    private final String responseMessage;
    private final Status status;

    public ATMResponseDTO(Status status, String responseMessage, TreeMap<String, Integer> bills) {
        this.responseMessage = responseMessage;
        this.status = status;
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

    public String getResponseMessage() {
        return responseMessage;
    }

    public TreeMap<String, Integer> getBills() {
        return bills;
    }

    public Status getStatus() {
        return status;
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
