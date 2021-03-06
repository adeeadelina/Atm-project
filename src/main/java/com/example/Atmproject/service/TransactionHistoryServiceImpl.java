package com.example.Atmproject.service;

import com.example.Atmproject.util.TransactionEntity;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.TreeSet;

@Service
public class TransactionHistoryServiceImpl implements HistoryService {
    private final TreeSet<TransactionEntity> transactions;

    public TransactionHistoryServiceImpl() {
        transactions = new TreeSet<>((o1, o2) -> o2.compareTo(o1));
    }

    public TreeSet<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void addTransaction(TransactionEntity transaction) {
        transactions.add(transaction);
    }

    public Table createFilteredTable(int mins) {

        float[] pointColumnWidths = {140F, 200F, 200F};
        Table table = new com.itextpdf.layout.element.Table(pointColumnWidths);

        for (TransactionEntity entity : transactions) {
            if (entity.getTime().isAfter(LocalDateTime.now().minusMinutes(mins))) {
                table.addCell(new Cell().add(entity.getTimeFormatPDF()));
                table.addCell(new Cell().add(entity.getRequestFormatPDF()));
                table.addCell(new Cell().add(entity.getResponseFormatPDF()));

            }
        }

        return table;
    }

    public Table createTable() {
        float[] pointColumnWidths = {140F, 200F, 200F};
        Table table = new com.itextpdf.layout.element.Table(pointColumnWidths);

        for (TransactionEntity entity : transactions) {
            table.addCell(new Cell().add(entity.getTimeFormatPDF()));
            table.addCell(new Cell().add(entity.getRequestFormatPDF()));
            table.addCell(new Cell().add(entity.getResponseFormatPDF()));
        }
            return table;
    }

}
