package com.relatedata.cve.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relatedata.cve.daos.CveDto;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class JsonReaderService {
    @Value("classpath:CVE.json")
    private Resource resourceFile;


    public List<CveDto> getListOfCVEItems(){
            Object obj = getJSONFile();
            List<CveDto> cveDtos = new ArrayList<>();
            JSONObject cveJson = (JSONObject) obj;
            JSONArray cveArrayJson = (JSONArray) cveJson.get("CVE_Items");
            Iterator<JSONObject> iterator = cveArrayJson.iterator();
            while (iterator.hasNext()) {
                JSONObject cveItem = iterator.next();
                if (cveItem.get("impact") != null) {
                    JSONObject impact = (JSONObject) cveItem.get("impact");
                    if (impact.get("baseMetricV2") != null) {
                        JSONObject baseMetricV2 = (JSONObject) impact.get("baseMetricV2");
                        String severity = baseMetricV2.get("severity").toString();
                        String publishedDate = cveItem.get("publishedDate").toString();
                        String year = publishedDate.substring(0, 4);
                        String month = publishedDate.substring(5, 7);
                        cveDtos.add(new CveDto(year, month, severity));
                    }

                }
            }

        return cveDtos;
    }


    public Object getJSONFile() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(resourceFile.getFile()));
            return obj;
        } catch (Exception e) {
            System.out.println(e);
        }
        throw new RuntimeException("Something is wrong");
    }
    public Map<String, Map<String, Map<String, Long>>> groupedCVEbyYearThenByMonth() {
        List<CveDto> cveDtos = getListOfCVEItems();
        Map<String, Map<String, Map<String, Long>>> groupedCVEbyYearThenByMonth = cveDtos.stream()
                .collect(Collectors.groupingBy(CveDto::getYear, Collectors.groupingBy(CveDto::getMonth, Collectors.groupingBy(CveDto::getImpact, Collectors.counting()))));

        return groupedCVEbyYearThenByMonth;
    }

    public Map<String, Map<String, Long>> groupedCVEbyYearThenByImpact() {
        List<CveDto> cveDtos = getListOfCVEItems();
        Map<String,Map<String, Long>> groupedCVEbyYearThenByImpact = cveDtos.stream()
                .collect(Collectors.groupingBy(CveDto::getYear, Collectors.groupingBy(CveDto::getImpact, Collectors.counting())));

        return groupedCVEbyYearThenByImpact;
    }

}
