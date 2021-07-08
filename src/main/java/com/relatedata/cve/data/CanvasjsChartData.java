package com.relatedata.cve.data;

import com.relatedata.cve.daos.CveDto;

import java.util.*;
import java.util.stream.Collectors;

public class CanvasjsChartData {
    static Map<Object, Object> map;
    static List<List<Map<Object, Object>>> list;
    static List<Map<Object, Object>> dataPoints1;
    static List<Map<Object, Object>> dataPoints2;
    static List<Map<Object, Object>> dataPoints3;
    static List<Map<Object, Object>> dataPoints4;
    static Long pointX;
    static Long incrementPoint;


    public static void initialize() {
        map = null;
        list = new ArrayList<List<Map<Object, Object>>>();
        dataPoints1 = new ArrayList<Map<Object, Object>>();
        dataPoints2 = new ArrayList<Map<Object, Object>>();
        dataPoints3 = new ArrayList<Map<Object, Object>>();
        pointX = 1489689000000L;
        incrementPoint = 0L;
    }

    public static List<List<Map<Object, Object>>> getCanvasjsDataList(List<CveDto> cveDtos) {
        initialize();
        Map<String, Map<String, Map<String, Long>>> groupedCVEbyYearThenByMonth = cveDtos.stream()
                .collect(Collectors.groupingBy(CveDto::getYear, Collectors.groupingBy(CveDto::getMonth, Collectors.groupingBy(CveDto::getImpact, Collectors.counting()))));
        List<String> years = sortList(new ArrayList<>(groupedCVEbyYearThenByMonth.keySet()));

        int numberOfPoints = countPoints(groupedCVEbyYearThenByMonth);
        incrementPoint = (long) 518400000 / numberOfPoints;

        for (String s : years) {
            Map<String, Map<String, Long>> cveGroupedByMonth = groupedCVEbyYearThenByMonth.get(s);
            List<String> months = sortList(new ArrayList<>(cveGroupedByMonth.keySet()));
            for (String s1 : months) {
                addPointsOnChart(cveGroupedByMonth.get(s1));
            }
        }

        list.add(dataPoints1);
        list.add(dataPoints2);
        list.add(dataPoints3);

        return list;
    }

    public static void addPointsOnChart(Map<String, Long> severityNoByMonth) {

        for (String s : severityNoByMonth.keySet()) {

            if (s.equals("HIGH")) {
                map = new HashMap<Object, Object>();
                map.put("x", pointX);
                map.put("y", severityNoByMonth.get(s));
                dataPoints1.add(map);
            } else if (s.equals("MEDIUM")) {
                map = new HashMap<Object, Object>();
                map.put("x", pointX);
                map.put("y", severityNoByMonth.get(s));
                dataPoints2.add(map);
            } else if (s.equals("LOW")) {
                map = new HashMap<Object, Object>();
                map.put("x", pointX);
                map.put("y", severityNoByMonth.get(s));
                dataPoints3.add(map);
            }
        }
        pointX += incrementPoint;

    }

    public static List<String> sortList(List<String> list) {
        Collections.sort(list);
        return list;
    }

    public static int countPoints(Map<String, Map<String, Map<String, Long>>> list) {
        int result = 0;
        for (String s : list.keySet()) {
            System.out.println(s);
            result += list.get(s).keySet().size();
        }
        return result;
    }
}
