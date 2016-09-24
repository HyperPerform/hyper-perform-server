package me.hyperperform.user.response;

import java.util.ArrayList;

/**
 * Created by rohan on 2016/09/24.
 */
public class GetManagedListResponse
{
    private class ListUnit
    {
        String firstname;
        String lastname;
        String email;
        double score;
        String performance;

        public ListUnit(String firstname, String lastname, String email, double score, String performance)
        {
            this.firstname = firstname;
            this.lastname = lastname;
            this.email = email;
            this.score = score;
            this.performance = performance;
        }
    }

    private int size;
    private ArrayList<ListUnit> data;

    public GetManagedListResponse() {
        size = 0;
        data = new ArrayList<ListUnit>();
    }

    private void addToList(String firstname, String lastname, String email, double score, String performance) {
        data.add(new ListUnit(firstname, lastname, email, score, performance));
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<ListUnit> getData() {
        return data;
    }

    public void setData(ArrayList<ListUnit> data) {
        this.data = data;
    }
}
