package com.example.Atmproject.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

public class PDFGenerator {
    private final Document document;
    private final PdfDocument pdfDocument;

    // type -> report/ mail
    public PDFGenerator(String type, Table table) throws Exception {
        String dest = "sample.pdf";
        PdfWriter writer = new PdfWriter(dest);

        pdfDocument = new PdfDocument(writer);

        pdfDocument.addNewPage();

        document = new Document(pdfDocument);

        if(type.equals("report")) {
            Table tableNew = createHistoryTable();
            document.add(tableNew);
        }

        document.add(table);
        closePDF();
    }

    public Document getDocument() {
        return document;
    }

    public void closePDF() {
        document.close();
    }

    public Table createHistoryTable() {
        Paragraph paragraph = new Paragraph("Here is your history:");
        document.add(paragraph);

        float[] pointColumnWidths = {140F, 200F, 200F};
        Table table = new Table(pointColumnWidths);
        table.addCell(new Cell().add("Time"));
        table.addCell(new Cell().add("Request"));
        table.addCell(new Cell().add("Response"));

        return table;
    }
}
