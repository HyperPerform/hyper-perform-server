package me.hyperperform.user;

/**
 * Defines position/job descriptions for various employees in the business
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/09/09
 */
public enum Position
{
    WebDeveloper("Web Developer"),
    SoftwareDeveloper("Software Developer"),
    Multimedia("Multimedia"),
    /**
     * Has more permissions than other users, also allows criteria not to allow PA score generation
     * as implemented in the strategy in the report generator
     */
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
