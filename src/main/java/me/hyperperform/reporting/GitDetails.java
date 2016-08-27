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

    public GitDetails(int size, ArrayList<ArrayList<GitPush>> data) {
        this.size = size;
        this.data = data;
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

}
