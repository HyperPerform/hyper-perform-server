package me.hyperperform.event.EntryExit;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/09/08
 * Feature:
 */

@Entity
@Table(name = "\"AccessEvent\"")
public class AccessEvent implements IEntryExit
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "Email")
    String email;

    @Column(name = "EmployeeID")
    String employeeID;

    @Column(name = "DeviceID")
    String deviceID;

    @Column(name = "Name")
    String name;

    @Column(name = "Surname")
    String surname;

    @Column(name = "Timestamp")
    Timestamp timestamp;

    @Column(name = "Day")
    Long day;

    public AccessEvent()
    {

    }

    public AccessEvent(String email, String employeeID, String deviceID, String name, String surname, String timestamp, Long day)
    {
        this.email = email;
        this.employeeID = employeeID;
        this.deviceID = deviceID;
        this.name = name;
        this.surname = surname;
        this.timestamp = Timestamp.valueOf(timestamp);
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

    public String getTimestamp()
    {
        return timestamp.toString();
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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
