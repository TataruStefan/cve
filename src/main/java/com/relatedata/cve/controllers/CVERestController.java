package com.relatedata.cve.controllers;

import com.relatedata.cve.services.JsonReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/cve")
public class CVERestController {

    @Autowired
    private JsonReaderService jsonReaderService;

    @GetMapping("/by-month")
    public Map<String, Map<String, Map<String, Long>>> getCVEAnalisysByMonth() {
        return jsonReaderService.groupedCVEbyYearThenByMonth();
    }

    @GetMapping("/by-impact")
    public Map<String, Map<String, Long>> getCVEAnalisysByImpact(){
        return jsonReaderService.groupedCVEbyYearThenByImpact();
    }
}
