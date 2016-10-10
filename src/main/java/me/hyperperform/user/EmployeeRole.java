package me.hyperperform.user;

/**
 * This Emum defines the level of permission as roles to access certain parts of the hyperperform system
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/09/09
 */
public enum EmployeeRole
{
    /**
     * Can do anything in the system has the highest amount of privileges
     */
    Super("Super"),

    /**
     * Can add more employees and view multiple employees profiles
     */
    Administrator("Administrator"),

    /**
     * Can only view information about themselves have the least amount of privileges
     */
    Employee("Employee");


    private String type;

    EmployeeRole(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
}
