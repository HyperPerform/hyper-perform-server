package me.hyperperform.rest;

import me.hyperperform.reporting.IReport;
import me.hyperperform.reporting.request.*;
import me.hyperperform.reporting.response.*;

import javax.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * REST wraps the {@see me.hyperperform.reporting.IReport} interface. A concrete realization is injected
 * into the object. The services provided by the {@see me.hyperperform.reporting.IReport} interface are then
 * made available through REST endpoints.
 *
 * @author : CodusMaximus
 * @version : 1.0
 * @since : 2016/08/10
 */
@Path("/report")
public class ReportingRest {

    @Inject
    IReport reportGenerator;

    /**
     * Exposes a service which allows for the requesting of summary data. Data is returned as a JSON object.
     *
     * @param getSummaryRequest - Request object containing information with regards to summary calculations such as time span.
     */
    @POST
    @Path("/getSummary")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getSummary(GetSummaryRequest getSummaryRequest)
    {
//        GetSummaryRequest getSummaryRequest = new GetSummaryRequest();
//        getSummaryRequest.setName("Rohan");
//        getSummaryRequest.setStartDate("2016-01-01 00:00:01");
//        getSummaryRequest.setEndDate("2016-12-30 23:59:59");

        GetSummaryResponse getSummaryResponse = reportGenerator.getSummary(getSummaryRequest);

        return Response.status(200).entity(getSummaryResponse).build();
    }

    /**
     * Exposes a service which allows for the requesting of detailed data with regards to a particular
     * integration. Data is returned as a JSON object.
     *
     * @param getDetailsRequest - Request object containing information with regards to detailed data calculations such as time span and name of integration for which data is needed.
     */
    @POST
    @Path("/getDetails")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getDetails(GetDetailsRequest getDetailsRequest)
    {

//        GetDetailsRequest getDetailsRequest = new GetDetailsRequest();
//        getDetailsRequest.setName("Rohan");
//        getDetailsRequest.setStartDate("2016-01-01 00:00:01");
//        getDetailsRequest.setEndDate("2016-12-30 23:59:59");
//        getDetailsRequest.setType("git");

//        getDetailsRequest.setName("baxterthehacker");
//        getDetailsRequest.setStartDate("2015-01-01 00:00:01");
//        getDetailsRequest.setEndDate("2016-12-30 23:59:59");
//        getDetailsRequest.setType("git");

        GetDetailsResponse getDetailsResponse = reportGenerator.getDetails(getDetailsRequest);

        return Response.status(200)

                .entity(getDetailsResponse)
                .build();
    }

    /**
     * Exposes a service which allows for the requesting of a performance score. JSON object is returned.
     *
     * @param getScoreRequest - Request object containing information with regards to score calculations.
     */
    @POST
    @Path("/getScore")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getScore(GetScoreRequest getScoreRequest)
    {
//        GetScoreRequest getScoreRequest = new GetScoreRequest();
//        getScoreRequest.setName("Rohan");
//        getScoreRequest.setStartDate("2016-01-01 00:00:01");
//        getScoreRequest.setEndDate("2016-12-30 23:59:59");

        GetScoreResponse getScoreResponse = reportGenerator.getScore(getScoreRequest);

        return Response.status(200).entity(getScoreResponse).build();
    }
}
