package me.hyperperform.event.Calendar;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;


/**
 * Calendar Meeting POJO to store the data for a CalendarMeeting Event
 * Contains Annotations for Persistence that stores the data via JPA
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/07/06
 */



@Entity
@Table(name = "\"CalendarProject\"")
public class CalendarProject implements ICalendarEvent, Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "projectID")
    private int id;


    @Column(name = "eventID")
    private String eventID;

    @Column(name = "creator")
    private String creator;

    @Column(name = "calendarID")
    private String calendarID;

    @Column(name = "duedate")
    private Timestamp dueDate;

    @Column(name = "reponame")
    private String repoName;


    @Column(name = "collaborators")
    private ArrayList<String> collaborators;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    public CalendarProject()
    {

    }

    /***
     * Overloaded constructor initializing the POJO with all the necessary attributes
     * @param eventID sets the eventID attribute
     * @param calendarID sets the calendarID attribute
     * @param create sets the creator attribute
     * @param due sets the dueDate attribute
     * @param collaborators sets the collaborator's attribute
     * @param repoName sets the name of the repository for Version Control Systems
     * @param time sets the timestamp attribute
     */
    public CalendarProject(String eventID, String calendarID, String create, String due, String repoName, ArrayList<String> collaborators, String time)
    {
        setEventID(eventID);
        setCalendarID(calendarID);
        setCreator(create);
        if(due.equals(null))
            setDueDate(null);
        else
            setDueDate(Timestamp.valueOf(due));

        setRepoName(repoName);
        setCollaborators(collaborators);
        setTimestamp(Timestamp.valueOf(time));

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

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
