package org.certdetails.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class DropdownController {

    @GetMapping("/api/dropdowns")
    public List<String> getDropdownValues(@RequestParam String checkbox) {
        // Mock data - in real scenario, fetch from database
        switch (checkbox) {
            case "Dev":
                return Arrays.asList("Option 1-1", "Option 1-2", "Option 1-3");
            case "QA":
                return Arrays.asList("Option 2-1", "Option 2-2", "Option 2-3");
            case "Prod":
                return Arrays.asList("Option 3-1", "Option 3-2", "Option 3-3");
        }
        return Collections.emptyList();
    }
}