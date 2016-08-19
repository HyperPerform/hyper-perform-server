package me.hyperperform.event.Calendar;

import me.hyperperform.event.IEvent;
import java.sql.Timestamp;

/**
 * Calendar Interface for all the calendar events
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/07/06
 */


public interface ICalendarEvent extends IEvent
{
    /**
     *
     * @return The eventID of the calendar
     */
    public String getEventID();

    /**
     *
     * @param eventID initialize the eventID of the event
     */
    public void setEventID(String eventID);

    /**
     *
     * @return The calendarId of the event
     */
    public String getCalendarID();

    /**
     *
     * @param creator initialize the creator of the event
     */
    public void setCreator(String creator);

    /**
     *
     * @return The creator of the event
     */
    public String getCreator();

    /**
     *
     * @param calendarID initialize calendarID of the event
     */
    public void setCalendarID(String calendarID);

    /**
     *
     * @return The DueDate of the event
     */
    public Timestamp getDueDate();

    /**
     *
     * @param dueDate initialize the due date of the event
     */
    public void setDueDate(Timestamp dueDate);

    /**
     *
     * @param timestamp initialize the date the event was created
     */
    public void setDate(Timestamp timestamp);
}