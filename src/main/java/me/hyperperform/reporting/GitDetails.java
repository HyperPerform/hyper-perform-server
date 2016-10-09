package me.hyperperform.reporting;

import me.hyperperform.event.Git.GitPush;

import java.util.ArrayList;

/**
 * hyper-perform
 * Group: CodusMaximus
 * Date: 2016/08/27
 * Feature:
 */
public class GitDetails
{
    private int size;
    private ArrayList<ArrayList<GitPush>> data;
    private ArrayList<GraphData<String, Long>> graphData;
    private long totalCommits;

    public GitDetails(int size, ArrayList<ArrayList<GitPush>> data, ArrayList<GraphData<String, Long>> graphData, long totalCommits) {
        this.size = size;
        this.data = data;
        this.graphData = graphData;
        this.totalCommits = totalCommits;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<ArrayList<GitPush>> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayList<GitPush>> data) {
        this.data = data;
    }

    public ArrayList<GraphData<String, Long>> getGraphData() {
        return graphData;
    }

    public void setGraphData(ArrayList<GraphData<String, Long>> graphData) {
        this.graphData = graphData;
    }

    public long getTotalCommits() {
        return totalCommits;
    }

    public void setTotalCommits(long totalCommits) {
        this.totalCommits = totalCommits;
    }
}
