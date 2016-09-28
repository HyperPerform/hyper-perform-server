package me.hyperperform.reporting;

import me.hyperperform.event.EntryExit.AccessEvent;

import java.util.ArrayList;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/09/21
 * Feature:
 */
public class AccessDetails
{
    private ArrayList<AccessEvent> data;
    private GraphData<String, Long> graphData;

    public AccessDetails(ArrayList<AccessEvent> data, GraphData<String, Long> graphData)
    {
        this.data = data;
        this.graphData = graphData;
    }

    public ArrayList<AccessEvent> getData()
    {
        return data;
    }

    public void setData(ArrayList<AccessEvent> data)
    {
        this.data = data;
    }

    public GraphData<String, Long> getGraphData() {
        return graphData;
    }

    public void setGraphData(GraphData<String, Long> graphData) {
        this.graphData = graphData;
    }
}
