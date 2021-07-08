package com.relatedata.cve.controllers;

import com.relatedata.cve.services.CanvasjsChartService;
import com.relatedata.cve.services.JsonReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class ChartController {

    @Autowired
    private CanvasjsChartService canvasjsChartService;

    @Autowired
    private JsonReaderService jsonReaderService;

    @GetMapping("chart")
    public String getChart(ModelMap modelMap) {
        List<List<Map<Object, Object>>> canvasjsDataList = canvasjsChartService.getCanvasjsChartData(jsonReaderService.getListOfCVEItems());
        modelMap.addAttribute("dataPointsList", canvasjsDataList);
        return "chartLine";
    }
}
