package me.hyperperform.user.request;

import java.sql.Timestamp;

/**
 * Request object for getManagedList method found in {@see me.hyperperform.rest.LoginRest}.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/09/24
 */

public class GetManagedListRequest
{
    private Timestamp startDate;
    private Timestamp endDate;

    public GetManagedListRequest() {
    }

    public GetManagedListRequest(Timestamp endDate, Timestamp startDate) {
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = Timestamp.valueOf(startDate);
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = Timestamp.valueOf(endDate);
    }
}
