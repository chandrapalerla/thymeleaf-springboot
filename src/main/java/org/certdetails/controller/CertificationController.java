package org.certdetails.controller;

import org.certdetails.model.ReportRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CertificationController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("reportRequest", new ReportRequest());
        return "index";
    }
}
