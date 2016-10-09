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
    private ArrayList<GraphData> graphData;

    public TravisDetails(int size, ArrayList<ArrayList<TravisEvent>> data) {
        this.size = size;
        this.data = data;
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

    public ArrayList<GraphData> getGraphData() {
        return graphData;
    }

    public void setGraphData(ArrayList<GraphData> graphData) {
        this.graphData = graphData;
    }
}
