package me.hyperperform.reporting;

import me.hyperperform.event.Git.GitIssue;

import java.util.ArrayList;

/**
 * Created by rohan on 2016/08/29.
 */
public class GitIssueDetails {

    private int size;
    private ArrayList<ArrayList<GitIssue>> data;

    public GitIssueDetails(int size, ArrayList<ArrayList<GitIssue>> data) {
        this.size = size;
        this.data = data;
    }

    public ArrayList<ArrayList<GitIssue>> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayList<GitIssue>> data) {
        this.data = data;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
