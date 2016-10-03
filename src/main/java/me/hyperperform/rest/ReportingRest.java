package me.hyperperform.rest;

import me.hyperperform.reporting.IReport;
import me.hyperperform.reporting.request.*;
import me.hyperperform.reporting.response.*;

import javax.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by rohan on 2016/08/10.
 */

@Path("/report")
public class ReportingRest {

    @Inject
    IReport reportGenerator;

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
