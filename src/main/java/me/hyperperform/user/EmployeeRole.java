package me.hyperperform.user;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/09/09
 * Feature:
 */
public enum EmployeeRole
{
    Super("Super"),
    Administrator("Administrator"),
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
