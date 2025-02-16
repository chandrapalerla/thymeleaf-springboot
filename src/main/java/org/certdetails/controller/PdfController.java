package org.certdetails.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

@RestController
public class PdfController {

    //    @PostMapping("/generate-pdf")
//    public void generatePdf(HttpServletResponse response) throws IOException, DocumentException {
//        response.setContentType("application/pdf");
//        response.setHeader("Content-Disposition", "attachment; filename=dropdowns.pdf");
//
//        Document document = new Document();
//        PdfWriter.getInstance(document, response.getOutputStream());
//
//        document.open();
//        document.add(new Paragraph("PDF with 5 Hardcoded Columns"));
//        PdfPTable table = new PdfPTable(5);
//        for (int i = 1; i <= 5; i++) {
//            table.addCell("Column " + i);
//        }
//        document.add(table);
//        document.close();
//    }
    @PostMapping("/generate-pdf")
    public ResponseEntity<byte[]> generatePdf(HttpServletResponse response) throws IOException, DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();

        PdfPTable table = new PdfPTable(6);// 6 columns
        table.addCell("ID");
        table.addCell("Server Name");
        table.addCell("Certificate Name");
        table.addCell("EnvironMent Name");
        table.addCell("Expiration Date");
        table.addCell("Status");

        // Hardcoded data
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            table.addCell("1");
            table.addCell("Server 1");
            table.addCell("Cert A");
            table.addCell("QA");
            table.addCell(sdf.format(sdf.parse("2024-01-15")));
            table.addCell("Active");


            table.addCell("2");
            table.addCell("Server 2");
            table.addCell("Cert B");
            table.addCell("Dev");
            table.addCell(sdf.format(sdf.parse("2024-03-20")));
            table.addCell("Pending");

            table.addCell("3");
            table.addCell("Server 2");
            table.addCell("Cert E");
            table.addCell("pod");
            table.addCell(sdf.format(sdf.parse("2024-09-25")));
            table.addCell("Pending");

        } catch (Exception e) {
            e.printStackTrace(); // Handle parsing exceptions
        }

        document.add(table);
        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "certificates.pdf"); // Set filename

        return new ResponseEntity<>(baos.toByteArray(), headers, org.springframework.http.HttpStatus.OK);
    }
}