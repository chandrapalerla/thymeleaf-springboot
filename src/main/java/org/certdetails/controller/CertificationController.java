package org.certdetails.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class CertificationController {
    @GetMapping("/")
    public String index(Model model) {
       // model.addAttribute("dropdownOptions", new ArrayList<>()); // Initially empty
        return "index";
    }

    @PostMapping("/updateDropdown")
    public String updateDropdown(@RequestParam(value = "checkboxes", required = false) List<String> checkboxes, Model model) {
        List<String> options = new ArrayList<>();
        if (checkboxes != null) {
            if (checkboxes.contains("option1")) {
                options.add("Dropdown Option 1");
            }
            if (checkboxes.contains("option2")) {
                options.add("Dropdown Option 2");
            }
            if (checkboxes.contains("option3")) {
                options.add("Dropdown Option 3");
            }
        }
        model.addAttribute("dropdownOptions", options);
        return "index"; // Return to the same page with updated dropdown
    }

    @PostMapping("/generatePdf")
    public ResponseEntity<byte[]> generatePdf(HttpServletResponse response) throws IOException, DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();

        PdfPTable table = new PdfPTable(5); // 5 columns
        table.addCell("ID");
        table.addCell("Certificate Name");
        table.addCell("Expiration Date");
        table.addCell("Status");
        table.addCell("Server Name");

        // Hardcoded data
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            table.addCell("1");
            table.addCell("Cert A");
            table.addCell(sdf.format(sdf.parse("2024-01-15")));
            table.addCell("Active");
            table.addCell("Server 1");

            table.addCell("2");
            table.addCell("Cert B");
            table.addCell(sdf.format(sdf.parse("2024-03-20")));
            table.addCell("Pending");
            table.addCell("Server 2");

            table.addCell("3");
            table.addCell("Cert C");
            table.addCell(sdf.format(sdf.parse("2023-11-01")));
            table.addCell("Expired");
            table.addCell("Server 1");

            table.addCell("4");
            table.addCell("Cert D");
            table.addCell(sdf.format(sdf.parse("2025-06-10")));
            table.addCell("Active");
            table.addCell("Server 3");

            table.addCell("5");
            table.addCell("Cert E");
            table.addCell(sdf.format(sdf.parse("2024-09-25")));
            table.addCell("Pending");
            table.addCell("Server 2");

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
