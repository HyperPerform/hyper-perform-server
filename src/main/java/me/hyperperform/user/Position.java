package me.hyperperform.user;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/09/09
 * Feature:
 */
public enum Position
{
    WebDeveloper("Web Developer"),
    SoftwareDeveloper("Software Developer"),
    Multimedia("Multimedia"),
    Manager("Manager");

    private String type;

    Position(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
}
