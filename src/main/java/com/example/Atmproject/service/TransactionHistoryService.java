package com.example.Atmproject.service;

import com.example.Atmproject.util.TransactionEntity;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Service;

import java.util.TreeSet;

@Service
public class TransactionHistoryService {
    private TreeSet<TransactionEntity> transactions;

    public TransactionHistoryService() {
        transactions = new TreeSet<>((o1,o2) -> o2.compareTo(o1));
    }

    public TreeSet<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void addTransaction(TransactionEntity transaction) {
        transactions.add(transaction);
    }

    public Table createTableRow(TransactionEntity entity) {
        float[] pointColumnWidths = {140F, 200F, 200F};
        Table table = new com.itextpdf.layout.element.Table(pointColumnWidths);

        table.addCell(new Cell().add(entity.getTimeFormatPDF()));
        table.addCell(new Cell().add(entity.getRequestFormatPDF()));
        table.addCell(new Cell().add(entity.getResponseFormatPDF()));

        return table;
    }

    public Table createTransactionsTable() {
        float[] pointColumnWidths = {140F, 200F, 200F};
        Table table = new com.itextpdf.layout.element.Table(pointColumnWidths);

        for (TransactionEntity entity : transactions) {
            table.addCell(new Cell().add(entity.getTimeFormatPDF()));
            if (entity.getType().equals("transaction")) {
                table.addCell(new Cell().add(entity.getTransactionRequestFormatPDF()));
                table.addCell(new Cell().add(entity.getTransactionResponseFormatPDF()));
            } else {
                table.addCell(new Cell().add(entity.getRequestFormatPDF()));
                table.addCell(new Cell().add(entity.getResponseFormatPDF()));
            }
        }

        return table;
    }
}
