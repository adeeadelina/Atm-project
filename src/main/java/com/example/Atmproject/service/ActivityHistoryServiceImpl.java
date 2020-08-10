package com.example.Atmproject.service;

import com.example.Atmproject.util.ActivityEntity;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.TreeSet;

@Service
public class ActivityHistoryServiceImpl implements HistoryService {
    private TreeSet<ActivityEntity> activities;

    public ActivityHistoryServiceImpl() {
        activities = new TreeSet<>((o1, o2) -> o2.compareTo(o1));
    }

    public TreeSet<ActivityEntity> getActivities() {
        return activities;
    }

    public void addActivity(ActivityEntity activity) {
        activities.add(activity);
    }

    public Table createFilteredTable(int mins) {

        float[] pointColumnWidths = {140F, 200F, 200F};
        Table table = new com.itextpdf.layout.element.Table(pointColumnWidths);

        for (ActivityEntity entity : activities) {
            if (entity.getTime().isAfter(LocalDateTime.now().minusMinutes(mins))) {
                createTableAux(table, entity);
            }
        }

        return table;
    }


    public Table createTable() {
        float[] pointColumnWidths = {140F, 200F, 200F};
        Table table = new com.itextpdf.layout.element.Table(pointColumnWidths);

        for (ActivityEntity entity : activities) {
            createTableAux(table, entity);
        }

        return table;
    }

    private void createTableAux(Table table, ActivityEntity entity) {
        table.addCell(new Cell().add(entity.getTimeFormatPDF()));
        if (entity.getType().equals("transaction")) {
            table.addCell(new Cell().add(entity.getTransactionRequestFormatPDF()));
            table.addCell(new Cell().add(entity.getTransactionResponseFormatPDF()));
        } else {
            table.addCell(new Cell().add(entity.getRequestFormatPDF()));
            table.addCell(new Cell().add(entity.getResponseFormatPDF()));
        }
    }
}
