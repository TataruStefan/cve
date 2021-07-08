package com.relatedata.cve.services;

import com.relatedata.cve.daos.CanvasjsChartDao;
import com.relatedata.cve.daos.CveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CanvasjsChartService {

    @Autowired
    private CanvasjsChartDao canvasjsChartDao;

    public List<List<Map<Object, Object>>> getCanvasjsChartData(List<CveDto> cveDtos) {
        return canvasjsChartDao.getCanvasjsChartData(cveDtos);
    }
}
