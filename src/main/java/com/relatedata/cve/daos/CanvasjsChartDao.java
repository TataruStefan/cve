package com.relatedata.cve.daos;

import com.relatedata.cve.data.CanvasjsChartData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CanvasjsChartDao {

    public List<List<Map<Object, Object>>> getCanvasjsChartData(List<CveDto> cveDtos) {
        return CanvasjsChartData.getCanvasjsDataList(cveDtos);
    }
}
