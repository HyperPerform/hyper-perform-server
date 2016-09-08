package me.hyperperform.event.EntryExit;

import java.sql.Timestamp;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/09/08
 * Feature:
 */
public class AccessEvent implements IEntryExit
{
    String employeeID;
    String deviceID;
    String name;
    String surname;
    Timestamp timestamp;
    Long day;

    public AccessEvent(String employeeID, String deviceID, String name, String surname, Timestamp timestamp, Long day)
    {
        this.employeeID = employeeID;
        this.deviceID = deviceID;
        this.name = name;
        this.surname = surname;
        this.timestamp = timestamp;
        this.day = day;
    }


    public String getEmployeeID()
    {
        return employeeID;
    }

    public void setEmployeeID(String employeeID)
    {
        this.employeeID = employeeID;
    }

    public String getDeviceID()
    {
        return deviceID;
    }

    public void setDeviceID(String deviceID)
    {
        this.deviceID = deviceID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public Timestamp getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp)
    {
        this.timestamp = timestamp;
    }

    public Long getDay()
    {
        return day;
    }

    public void setDay(Long day)
    {
        this.day = day;
    }
}
