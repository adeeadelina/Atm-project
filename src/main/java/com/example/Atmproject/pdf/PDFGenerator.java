package com.example.Atmproject.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

public class PDFGenerator {
    private Document document;
    private PdfDocument pdfDocument;

    public PDFGenerator(Table table) throws Exception {
        String dest = "sample.pdf";
        PdfWriter writer = new PdfWriter(dest);

        pdfDocument = new PdfDocument(writer);

        pdfDocument.addNewPage();

        document = new Document(pdfDocument);

        Paragraph paragraph = new Paragraph("Here is your history:");
        document.add(paragraph);

        float [] pointColumnWidths = {140F, 200F, 200F};
        Table tableNew = new Table(pointColumnWidths);
        tableNew.addCell(new Cell().add("Time"));
        tableNew.addCell(new Cell().add("Request"));
        tableNew.addCell(new Cell().add("Response"));


        document.add(tableNew);
        document.add(table);
        document.close();
    }

    public Document getDocument() {
        return document;
    }

    public void closePDF() {
        document.close();
    }

    public void addTable(Table table) {
        document.add(table);
    }
}
