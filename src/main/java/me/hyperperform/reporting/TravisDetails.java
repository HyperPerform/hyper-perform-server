package me.hyperperform.reporting;

import me.hyperperform.event.Travis.TravisEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohan on 2016/08/13.
 */
public class TravisDetails
{
    private int size;
    private ArrayList<ArrayList<TravisEvent>> data;
    private ArrayList<Double[]> graphData;

    public TravisDetails(int size, ArrayList<ArrayList<TravisEvent>> data, ArrayList<Double[]> graphData) {
        this.size = size;
        this.data = data;
        this.graphData = graphData;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<ArrayList<TravisEvent>> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayList<TravisEvent>> data) {
        this.data = data;
    }

    public ArrayList<Double[]> getGraphData() {
        return graphData;
    }

    public void setGraphData(ArrayList<Double[]> graphData) {
        this.graphData = graphData;
    }
}
