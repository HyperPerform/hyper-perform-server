package me.hyperperform.reporting.request;

import java.sql.Timestamp;

/**
 * Created by rohan on 2016/08/19.
 */
public class CalculateScoreRequest {

    private String name;
    private Timestamp startDate;
    private Timestamp endDate;

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
