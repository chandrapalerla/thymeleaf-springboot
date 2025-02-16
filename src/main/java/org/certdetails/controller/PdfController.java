package org.certdetails.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class PdfController {

    @PostMapping("/generate-pdf")
    public void generatePdf(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=dropdowns.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        document.add(new Paragraph("PDF with 5 Hardcoded Columns"));
        PdfPTable table = new PdfPTable(5);
        for (int i = 1; i <= 5; i++) {
            table.addCell("Column " + i);
        }
        document.add(table);
        document.close();
    }
}