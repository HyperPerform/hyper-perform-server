package me.hyperperform.event.Calendar;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Calendar Meeting POJO to store the data for a CalendarMeeting Event
 * Contains Annotations for Persistence that stores the data via JPA
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/07/06
 */


@Entity
@Table(name = "\"CalendarMeeting\"")
public class CalendarMeeting implements ICalendarEvent, Serializable
{

    /**
     * Auto-generated primary key for the persistence
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meetingID")
    private int id;

    /**
     * EventID of the Google Calender Event
     */
    @Column(name = "eventID")
    private String eventID;

    /**
     * Creator of the Event
     */
    @Column(name = "creator")
    private String creator;


    /**
     * CalendarID of the Event
     */
    @Column(name = "calendarID")
    private String calendarID;

    /**
     * Date and time the event is to be due
     */
    @Column(name = "duedate")
    private Timestamp dueDate;

    /**
     * Location where the Meeting is taking place
     */
    @Column(name = "location")
    private String location;

    /**
     * The Attendees of event with the status of each
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "attendees")
    private Map<String, AttendeeState> attendees;

    /**
     * Date and time the event was created
     */
    @Column(name = "timestamp")
    private Timestamp timestamp;

    public CalendarMeeting()
    {

    }

    /**
     * Overloaded constructor initializing the POJO with all the necessary details
     *
     * @param eventID sets the eventID attribute
     * @param calendarID sets the calendarID attribute
     * @param creator sets the creator attribute
     * @param due sets the dueDate attribute
     * @param location sets the location attribute
     * @param attendees sets the attendees attribute
     * @param time sets the timestamp attribute
     *
     */
    public CalendarMeeting(String eventID, String calendarID, String creator, String due, String location, Map<String, AttendeeState> attendees, String time)
    {
        setEventID(eventID);
        setCalendarID(calendarID);
        setCreator(creator);
        if(due.equals(null))
            setDueDate(null);
        else
            setDueDate(Timestamp.valueOf(due));
        setLocation(location);
        setAttendees(attendees);
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

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String loc)
    {
        location = loc;
    }

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public Map<String, AttendeeState> getAttendees()
    {
        return attendees;
    }

    public void setAttendees(Map<String, AttendeeState> attend)
    {
        attendees = new HashMap<String, AttendeeState>();

        attendees.putAll(attend);
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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
