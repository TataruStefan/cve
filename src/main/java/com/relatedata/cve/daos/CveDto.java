package com.relatedata.cve.daos;

public class CveDto {
    private String year;
    private String month;
    private String impact;

    public CveDto(String year, String month, String impact) {
        this.year = year;
        this.month = month;
        this.impact = impact;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }
}
