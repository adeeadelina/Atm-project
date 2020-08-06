package com.example.Atmproject.service;

import com.itextpdf.layout.element.Table;

public interface HistoryService {
    Table createFilteredTable(int mins);
    Table createTable();
}
