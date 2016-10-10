package me.hyperperform.reporting;

import me.hyperperform.event.EntryExit.AccessEvent;

import java.util.ArrayList;

/**
 * Object which is populated with processed access data. This object is usually encapsulated within
 * a {@see me.hyperperform.reporting.response.GetDetailsResponse} and is populated by the GetDetails method
 * in {@see me.hyperperform.reporting.IReport}.
 *
 * @author : CodusMaximus
 * @version : 1.0
 * @since : 2016/09/21
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
