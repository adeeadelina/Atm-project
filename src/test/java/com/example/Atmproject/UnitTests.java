package com.example.Atmproject;


import com.example.Atmproject.exception.ImpossibleSplitException;
import com.example.Atmproject.exception.IncorrectAmountException;
import com.example.Atmproject.exception.NotEnoughMoneyException;
import com.example.Atmproject.service.ATMServiceImpl;
import com.example.Atmproject.service.CashWithdrawalServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnitTests {



  //  @Mock
    private static CashWithdrawalServiceImpl newCashWithdrawalService;

//    @BeforeEach
//    public void setATM() throws Exception {
//        initMocks(this);
//       // newCashWithdrawalService = new CashWithdrawalServiceImpl();
//      //  newCashWithdrawalService.getAtmMachine().fillATM();
//    }

    //too much money
    @Test
    public void exceptionWithdraw1() {
        ATMServiceImpl atm = new ATMServiceImpl();
        newCashWithdrawalService = new CashWithdrawalServiceImpl(atm);
        NotEnoughMoneyException exception = assertThrows(NotEnoughMoneyException.class, () -> newCashWithdrawalService.withdraw(9200));
        assertEquals("Not enough money.", exception.getMessage());
        //assertEquals(new TreeMap<>(), newCashWithdrawalService.withdraw(9200));
    }

    //the exact amount
    @Test
    public void exceptionWithdraw2() throws NotEnoughMoneyException, ImpossibleSplitException, IncorrectAmountException {
        ATMServiceImpl atm = new ATMServiceImpl();
        newCashWithdrawalService = new CashWithdrawalServiceImpl(atm);        TreeMap<String, Integer> expectedResult = new TreeMap<>();
        expectedResult.put("ONE_RON(1)", 100);
        expectedResult.put("FIVE_RON(5)", 100);
        expectedResult.put("TEN_RON(10)", 100);
        expectedResult.put("FIFTY_RON(50)", 50);
        expectedResult.put("ONEHUNDRED_RON(100)", 50);
        TreeMap<String, Integer> actualResult = newCashWithdrawalService.withdraw(9100).getBills();
        assertEquals(expectedResult, actualResult);
    }

    //0 ron
//    @Test
//    public void exceptionWithdraw3() throws NotEnoughMoneyException, ImpossibleSplitException, IncorrectAmountException {
//      NotEnoughMoneyException thrown = assertThrows(NotEnoughMoneyException.class, () ->)
//        assertEquals(new TreeMap<>(), newCashWithdrawalService.withdraw(0));    }

    @Test
    public void multipleWithdraw1() throws NotEnoughMoneyException, ImpossibleSplitException, IncorrectAmountException {
        TreeMap<String, Integer> expectedResult1 = new TreeMap<>();
        expectedResult1.put("ONEHUNDRED_RON(100)", 50);
        TreeMap<String, Integer> actualResult1 = newCashWithdrawalService.withdraw(5000).getBills();
        assertEquals(expectedResult1, actualResult1);

        TreeMap<String, Integer> expectedResult2 = new TreeMap<>();
        expectedResult2.put("FIFTY_RON(50)", 50);
        TreeMap<String, Integer> actualResult2 = newCashWithdrawalService.withdraw(2500).getBills();
        assertEquals(expectedResult2, actualResult2);

        TreeMap<String, Integer> expectedResult3 = new TreeMap<>();
        expectedResult3.put("TEN_RON(10)", 98);
        TreeMap<String, Integer> actualResult3 = newCashWithdrawalService.withdraw(980).getBills();
        assertEquals(expectedResult3, actualResult3);
    }

    @Test
    public void multipleWithdraw2() throws NotEnoughMoneyException, ImpossibleSplitException, IncorrectAmountException {
        TreeMap<String, Integer> expectedResult1 = new TreeMap<>();
        expectedResult1.put("ONEHUNDRED_RON(100)", 50);
        TreeMap<String, Integer> actualResult1 = newCashWithdrawalService.withdraw(5000).getBills();
        assertEquals(expectedResult1, actualResult1);

        TreeMap<String, Integer> expectedResult2 = new TreeMap<>();
        expectedResult2.put("FIFTY_RON(50)", 50);
        TreeMap<String, Integer> actualResult2 = newCashWithdrawalService.withdraw(2500).getBills();
        assertEquals(expectedResult2, actualResult2);

        TreeMap<String, Integer> expectedResult3 = new TreeMap<>();
        expectedResult3.put("TEN_RON(10)", 98);
        TreeMap<String, Integer> actualResult3 = newCashWithdrawalService.withdraw(980).getBills();
        assertEquals(expectedResult3, actualResult3);

        TreeMap<String, Integer> expectedResult4 = new TreeMap<>();
        expectedResult4.put("TEN_RON(10)", 2);
        expectedResult4.put("FIVE_RON(5)", 26);
        TreeMap<String, Integer> actualResult4 = newCashWithdrawalService.withdraw(150).getBills();
        assertEquals(expectedResult4, actualResult4);
    }

    @Test
    public void multipleWithdraw3() throws NotEnoughMoneyException, ImpossibleSplitException, IncorrectAmountException {
        TreeMap<String, Integer> expectedResult1 = new TreeMap<>();
        expectedResult1.put("FIVE_RON(5)", 1);
        expectedResult1.put("ONE_RON(1)", 2);
        TreeMap<String, Integer> actualResult1 = newCashWithdrawalService.withdraw(7).getBills();
        assertEquals(expectedResult1, actualResult1);

        TreeMap<String, Integer> expectedResult2 = new TreeMap<>();
        expectedResult2.put("ONEHUNDRED_RON(100)", 1);
        TreeMap<String, Integer> actualResult2 = newCashWithdrawalService.withdraw(100).getBills();
        assertEquals(expectedResult2, actualResult2);

        TreeMap<String, Integer> expectedResult3 = new TreeMap<>();
        expectedResult3.put("ONEHUNDRED_RON(100)", 15);
        expectedResult3.put("TEN_RON(10)", 4);
        TreeMap<String, Integer> actualResult3 = newCashWithdrawalService.withdraw(1540).getBills();
        assertEquals(expectedResult3, actualResult3);

        TreeMap<String, Integer> expectedResult4 = new TreeMap<>();
        expectedResult4.put("ONEHUNDRED_RON(100)", 25);
        TreeMap<String, Integer> actualResult4 = newCashWithdrawalService.withdraw(2500).getBills();
        assertEquals(expectedResult4, actualResult4);
    }

    @Test
    public void multipleWithdraw4() throws NotEnoughMoneyException, ImpossibleSplitException, IncorrectAmountException {
        TreeMap<String, Integer> expectedResult1 = new TreeMap<>();
        expectedResult1.put("FIVE_RON(5)", 1);
        expectedResult1.put("ONE_RON(1)", 2);
        TreeMap<String, Integer> actualResult1 = newCashWithdrawalService.withdraw(7).getBills();
        assertEquals(expectedResult1, actualResult1);

        TreeMap<String, Integer> expectedResult2 = new TreeMap<>();
        expectedResult2.put("ONEHUNDRED_RON(100)", 1);
        expectedResult2.put("FIFTY_RON(50)", 1);
        expectedResult2.put("ONE_RON(1)", 1);
        TreeMap<String, Integer> actualResult2 = newCashWithdrawalService.withdraw(151).getBills();
        assertEquals(expectedResult2, actualResult2);

        TreeMap<String, Integer> expectedResult3 = new TreeMap<>();
        expectedResult3.put("ONEHUNDRED_RON(100)", 14);
        expectedResult3.put("FIFTY_RON(50)", 1);
        expectedResult3.put("TEN_RON(10)", 2);
        expectedResult3.put("FIVE_RON(5)", 1);
        expectedResult3.put("ONE_RON(1)", 2);
        TreeMap<String, Integer> actualResult3 = newCashWithdrawalService.withdraw(1477).getBills();
        assertEquals(expectedResult3, actualResult3);

        TreeMap<String, Integer> expectedResult4 = new TreeMap<>();
        expectedResult4.put("TEN_RON(10)", 2);
        expectedResult4.put("FIVE_RON(5)", 1);
        expectedResult4.put("ONE_RON(1)", 4);
        TreeMap<String, Integer> actualResult4 = newCashWithdrawalService.withdraw(29).getBills();
        assertEquals(expectedResult4, actualResult4);
    }

    @Test
    public void multipleWithdraw5() throws NotEnoughMoneyException, ImpossibleSplitException, IncorrectAmountException {
        TreeMap<String, Integer> expectedResult1 = new TreeMap<>();
        expectedResult1.put("ONE_RON(1)", 2);
        TreeMap<String, Integer> actualResult1 = newCashWithdrawalService.withdraw(2).getBills();
        assertEquals(expectedResult1, actualResult1);

        TreeMap<String, Integer> expectedResult2 = new TreeMap<>();
        expectedResult2.put("ONEHUNDRED_RON(100)", 1);
        expectedResult2.put("FIFTY_RON(50)", 1);
        expectedResult2.put("ONE_RON(1)", 1);
        TreeMap<String, Integer> actualResult2 = newCashWithdrawalService.withdraw(151).getBills();
        assertEquals(expectedResult2, actualResult2);

        TreeMap<String, Integer> expectedResult3 = new TreeMap<>();
        expectedResult3.put("ONEHUNDRED_RON(100)", 14);
        expectedResult3.put("FIFTY_RON(50)", 1);
        expectedResult3.put("TEN_RON(10)", 2);
        expectedResult3.put("FIVE_RON(5)", 1);
        expectedResult3.put("ONE_RON(1)", 2);
        TreeMap<String, Integer> actualResult3 = newCashWithdrawalService.withdraw(1477).getBills();
        assertEquals(expectedResult3, actualResult3);

        TreeMap<String, Integer> expectedResult4 = new TreeMap<>();
        expectedResult4.put("ONEHUNDRED_RON(100)", 35);
        expectedResult4.put("TEN_RON(10)", 2);
        expectedResult4.put("FIVE_RON(5)", 1);
        expectedResult4.put("ONE_RON(1)", 4);
        TreeMap<String, Integer> actualResult4 = newCashWithdrawalService.withdraw(3529).getBills();
        assertEquals(expectedResult4, actualResult4);

        NotEnoughMoneyException exception = assertThrows(NotEnoughMoneyException.class, () -> newCashWithdrawalService.withdraw(9200));
        assertEquals("Not enough money.", exception.getMessage());
    }

    @Test
    public void multipleWithdraw6() throws NotEnoughMoneyException, ImpossibleSplitException, IncorrectAmountException {
        TreeMap<String, Integer> expectedResult1 = new TreeMap<>();
        expectedResult1.put("ONEHUNDRED_RON(100)", 49);
        TreeMap<String, Integer> actualResult1 = newCashWithdrawalService.withdraw(4900).getBills();
        assertEquals(expectedResult1, actualResult1);

        TreeMap<String, Integer> expectedResult2 = new TreeMap<>();
        expectedResult2.put("ONEHUNDRED_RON(100)", 1);
        expectedResult2.put("TEN_RON(10)", 4);
        expectedResult2.put("FIVE_RON(5)", 1);
        expectedResult2.put("ONE_RON(1)", 4);
        TreeMap<String, Integer> actualResult2 = newCashWithdrawalService.withdraw(149).getBills();
        assertEquals(expectedResult2, actualResult2);

        TreeMap<String, Integer> expectedResult3 = new TreeMap<>();
        expectedResult3.put("FIFTY_RON(50)", 50);
        expectedResult3.put("TEN_RON(10)", 3);
        expectedResult3.put("ONE_RON(1)", 3);
        TreeMap<String, Integer> actualResult3 = newCashWithdrawalService.withdraw(2533).getBills();
        assertEquals(expectedResult3, actualResult3);

        TreeMap<String, Integer> expectedResult4 = new TreeMap<>();
        expectedResult4.put("TEN_RON(10)", 93);
        expectedResult4.put("FIVE_RON(5)", 14);
        TreeMap<String, Integer> actualResult4 = newCashWithdrawalService.withdraw(1000).getBills();
        assertEquals(expectedResult4, actualResult4);
    }

    @Test
    public void multipleWithdraw7() throws NotEnoughMoneyException, ImpossibleSplitException, IncorrectAmountException {
        TreeMap<String, Integer> expectedResult = new TreeMap<>();
        expectedResult.put("ONE_RON(1)", 100);
        expectedResult.put("FIVE_RON(5)", 100);
        expectedResult.put("TEN_RON(10)", 100);
        expectedResult.put("FIFTY_RON(50)", 50);
        expectedResult.put("ONEHUNDRED_RON(100)", 50);
        TreeMap<String, Integer> actualResult = newCashWithdrawalService.withdraw(9100).getBills();
        assertEquals(expectedResult, actualResult);

        NotEnoughMoneyException exception = assertThrows(NotEnoughMoneyException.class, () -> newCashWithdrawalService.withdraw(9200));
        assertEquals("Not enough money.", exception.getMessage());
    }


}

