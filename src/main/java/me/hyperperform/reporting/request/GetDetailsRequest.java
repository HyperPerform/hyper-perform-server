package me.hyperperform.reporting.request;

import java.sql.Timestamp;

/**
 * Created by rohan on 2016/08/23.
 */
public class GetDetailsRequest {

    private String name;
    private Timestamp startDate;
    private Timestamp endDate;
    private String type;

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = Timestamp.valueOf(endDate);
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = Timestamp.valueOf(startDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString(){
        String s = "Name: " + name + "\n";
        s += "Start date: " + startDate + "\n";
        s += "End date: " + endDate + "\n";
        s += "Type: " + type;

        return s;
    }

}
