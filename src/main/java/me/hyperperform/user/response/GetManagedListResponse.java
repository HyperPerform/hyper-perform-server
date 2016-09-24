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

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPerformance() {
            return performance;
        }

        public void setPerformance(String performance) {
            this.performance = performance;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }
    }

    private int size;
    private ArrayList<ListUnit> data;

    public GetManagedListResponse() {
        size = 0;
        data = new ArrayList<ListUnit>();
    }

    public void addToList(String firstname, String lastname, String email, double score, String performance) {
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
