package org.certdetails.controller;

import org.certdetails.model.DropdownRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DropdownController {

    @PostMapping("/dropdowns")
    @ResponseBody
    public List<String> getDropdownOptions(@RequestBody DropdownRequest dropdownRequest) { // Use @RequestBody
        List<String> options = new ArrayList<>();

        if (dropdownRequest.isDev()) {
            options.add("Dev Server 1");
            options.add("Dev Server 2");
        }
        if (dropdownRequest.isQa()) {
            options.add("QA Server 1");
            options.add("QA Server 2");
        }
        if (dropdownRequest.isProd()) {
            options.add("Prod Server 1");
            options.add("Prod Server 2");
        }
        return options;
    }

}