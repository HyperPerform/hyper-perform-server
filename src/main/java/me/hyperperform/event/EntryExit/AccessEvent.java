package me.hyperperform.event.EntryExit;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Access Event POJO to store the data for an entry/exit systems
 * Contains Annotations for Persistence that stores the data via JPA
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/08/09
 */

@Entity
@Table(name = "\"AccessEvent\"")
public class AccessEvent implements IEntryExit
{

    /**
     * Auto-generated primary key for the persistence
     */
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

    /**
     * This attribute is based on the day so that it can easily be monitored when the employee entered and exited the building and how many times
     */
    @Column(name = "Day")
    Long day;

    public AccessEvent()
    {

    }

    /**
     * Constructor initializing the POJO with all the attributes
     * @param email - the email of the user that has entered or left the building
     * @param employeeID - the employee ID of the user in the business
     * @param deviceID  - the device or location in which the person gained access
     * @param name - the name of the person
     * @param surname - the surname of the person
     * @param timestamp - the time the user gained access to the building
     * @param day - the day number to validate performance in which one can check logs easier
     */
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
