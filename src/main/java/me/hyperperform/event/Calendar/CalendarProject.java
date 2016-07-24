package me.hyperperform.event.Calendar;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Hyper-perform
 * Group: CodusMaximus
 * Date: 2016/07/06
 * Feature: Calendar
 */

@Entity
@Table(name = "\"CalendarProject\"")

public class CalendarProject implements ICalendarEvent, Serializable
{
    @Id
    private String eventID;
    private String creator;
    private String calendarID;
    private Timestamp dueDate;
    private String repoName;

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    //    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrayList<String> collaborators;
    private Timestamp timestamp;

    public CalendarProject()
    {

    }

    public CalendarProject(String eventID, String calendarID, Timestamp dueDate, String repoName, ArrayList<String> collaborators, Timestamp timestamp)
    {
        setEventID(eventID);
        setCalendarID(calendarID);
        setDueDate(dueDate);
        setRepoName(repoName);
        setCollaborators(collaborators);
        setTimestamp(timestamp);

    }
    public Timestamp getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getRepoName()
    {
        return repoName;
    }

    public void setRepoName(String rName)
    {
        repoName = rName;
    }

    public ArrayList<String> getCollaborators()
    {
        return collaborators;
    }

    public void setCollaborators(ArrayList<String> collabs)
    {
        collaborators = collabs;
    }

    public String getEventID()
    {
        return eventID;
    }

    public void setEventID(String eID)
    {
        eventID = eID;
    }

    public String getCalendarID()
    {
        return calendarID;
    }

    public void setCalendarID(String cID)
    {
        calendarID = cID;
    }

    public Timestamp getDueDate()
    {
        return dueDate;
    }

    public void setDueDate(Timestamp dDate)
    {
        dueDate = dDate;
    }

    public void setDate(Timestamp timestamp)
    {
       this.timestamp = timestamp;
    }
}