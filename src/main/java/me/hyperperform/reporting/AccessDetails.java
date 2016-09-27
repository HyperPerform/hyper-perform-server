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

    public AccessDetails(ArrayList<AccessEvent> data)
    {
        this.data = data;
    }
    public ArrayList<AccessEvent> getData()
    {
        return data;
    }

    public void setData(ArrayList<AccessEvent> data)
    {
        this.data = data;
    }


}
