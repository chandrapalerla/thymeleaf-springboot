package org.certdetails.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.certdetails.model.Certificate;
import org.certdetails.model.ReportRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PdfController {

    @PostMapping("/generate-pdf")
    public ResponseEntity<byte[]> generatePdf(@ModelAttribute("reportRequest") ReportRequest reportRequest, HttpServletResponse response) throws IOException, DocumentException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();

        PdfPTable table = new PdfPTable(5);
        table.addCell("ID");
        table.addCell("Certificate Name");
        table.addCell("Expiration Date");
        table.addCell("Status");
        table.addCell("Server Name");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<Certificate> certificates = getCertificateData(); // Your data source

        List<Certificate> filteredCertificates = certificates
                .stream()
                .filter(cert -> {
                    boolean devMatch = reportRequest.isDev() && cert.getServerName().contains("Dev");
                    boolean qaMatch = reportRequest.isQa() && cert.getServerName().contains("QA");
                    boolean prodMatch = reportRequest.isProd() && cert.getServerName().contains("Prod");
                    return devMatch || qaMatch || prodMatch;
                })
                .collect(Collectors.toList());

        try {
            for (Certificate cert : filteredCertificates) {
                table.addCell(String.valueOf(cert.getId()));
                table.addCell(cert.getCertificateName());
                table.addCell(sdf.format(cert.getExpirationDate()));
                table.addCell(cert.getStatus());
                table.addCell(cert.getServerName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        document.add(table);
        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "certificates.pdf");

        return new ResponseEntity<>(baos.toByteArray(), headers, org.springframework.http.HttpStatus.OK);
    }

    private List<Certificate> getCertificateData() {
        List<Certificate> certificates = new ArrayList<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            certificates.add(new Certificate(1, "Cert A", sdf.parse("2024-01-15"), "Active", "Server Dev1"));
            certificates.add(new Certificate(2, "Cert B", sdf.parse("2024-03-20"), "Pending", "Server QA1"));
            certificates.add(new Certificate(3, "Cert C", sdf.parse("2023-11-01"), "Expired", "Server Dev2"));
            certificates.add(new Certificate(4, "Cert D", sdf.parse("2025-06-10"), "Active", "Server Prod1"));
            certificates.add(new Certificate(5, "Cert E", sdf.parse("2024-09-25"), "Pending", "Server QA2"));
            certificates.add(new Certificate(6, "Cert F", sdf.parse("2024-09-25"), "Pending", "Server Dev3"));
            certificates.add(new Certificate(7, "Cert G", sdf.parse("2024-09-25"), "Pending", "Server Prod2"));
            certificates.add(new Certificate(8, "Cert H", sdf.parse("2024-09-25"), "Pending", "Server QA3"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return certificates;
    }
}