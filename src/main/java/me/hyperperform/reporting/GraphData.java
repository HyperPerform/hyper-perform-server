package me.hyperperform.reporting;

import java.util.ArrayList;

/**
 * Created by rohan on 2016/09/06.
 */
public class GraphData<T, U>
{
    private ArrayList<T> independent;
    private ArrayList<U> dependent;

    public GraphData() {
        independent = null;
        dependent = null;
    }

    public GraphData(ArrayList<T> independent, ArrayList<U> dependent) {
        this.dependent = dependent;
        this.independent = independent;
    }

    public ArrayList<T> getIndependent() {
        return independent;
    }

    public void setIndependent(ArrayList<T> independent) {
        this.independent = independent;
    }

    public ArrayList<U> getDependent() {
        return dependent;
    }

    public void setDependent(ArrayList<U> dependent) {
        this.dependent = dependent;
    }

    public String toString()
    {
        return (independent.toString() + "\n" + dependent.toString());
    }
}
