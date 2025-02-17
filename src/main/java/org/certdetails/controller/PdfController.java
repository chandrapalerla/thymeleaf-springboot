package org.certdetails.controller;

import com.itextpdf.text.DocumentException;
import org.certdetails.model.ReportRequest;
import org.certdetails.service.PdfGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PdfController {

    @Autowired
    protected PdfGenerationService pdfGeneration;

    @PostMapping("/generate-pdf")
    public ResponseEntity<byte[]> generatePdf(@ModelAttribute("reportRequest") ReportRequest reportRequest) throws DocumentException {
        return pdfGeneration.generatePdf(reportRequest);
    }
}