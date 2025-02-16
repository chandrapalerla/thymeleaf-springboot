package org.certdetails.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
}
