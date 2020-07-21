package com.example.Atmproject;

import java.util.Objects;

public class Bills {
    public int nrOfBills;
    public int typeOfBills;

    public Bills(int nrOfBills, int typeOfBills) {
        this.nrOfBills = nrOfBills;
        this.typeOfBills = typeOfBills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bills bills = (Bills) o;
        return nrOfBills == bills.nrOfBills &&
                typeOfBills == bills.typeOfBills;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nrOfBills, typeOfBills);
    }

    public String toString() {
        return "(" + nrOfBills + "x" + typeOfBills + ")";
    }
}
