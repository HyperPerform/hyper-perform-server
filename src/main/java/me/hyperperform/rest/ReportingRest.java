package me.hyperperform.rest;

import me.hyperperform.reporting.IReport;
import me.hyperperform.reporting.request.*;
import me.hyperperform.reporting.response.*;

import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by rohan on 2016/08/10.
 */

@Path("/report")
public class ReportingRest {

    @Inject
    IReport reportGenerator;

    @GET
//    @Consumes("application/json")
//    public Response getSummary(GetSummaryRequest getSummaryRequest)
    @Path("/getSummary")
    @Produces("application/json")
    public Response getSummary()
    {
        GetSummaryRequest getSummaryRequest = new GetSummaryRequest();
        getSummaryRequest.setName("Rohan");
        getSummaryRequest.setStartDate("2016-01-01 00:00:01");
        getSummaryRequest.setEndDate("2016-12-30 23:59:59");

        GetSummaryResponse getSummaryResponse = reportGenerator.getSummary(getSummaryRequest);

        return Response.status(200).entity(getSummaryResponse).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/getDetails")
    @Produces("application/json")
    public Response getDetails()
    {
        GetDetailsRequest getDetailsRequest = new GetDetailsRequest();
//        getDetailsRequest.setName("Rohan");
//        getDetailsRequest.setStartDate("2016-01-01 00:00:01");
//        getDetailsRequest.setEndDate("2016-12-30 23:59:59");
//        getDetailsRequest.setType("travis");

        getDetailsRequest.setName("baxterthehacker");
        getDetailsRequest.setStartDate("2015-01-01 00:00:01");
        getDetailsRequest.setEndDate("2016-12-30 23:59:59");
        getDetailsRequest.setType("git");


        GetDetailsResponse getDetailsResponse = reportGenerator.getDetails(getDetailsRequest);

        return Response.status(200).entity(getDetailsResponse).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/getScore")
    @Produces("application/json")
    public Response getScore()
    {
        GetScoreRequest getScoreRequest = new GetScoreRequest();
        getScoreRequest.setName("Rohan");
        getScoreRequest.setStartDate("2016-01-01 00:00:01");
        getScoreRequest.setEndDate("2016-12-30 23:59:59");

        GetScoreResponse getScoreResponse = reportGenerator.getScore(getScoreRequest);

        return Response.status(200).entity(getScoreResponse).header("Access-Control-Allow-Origin", "*").build();
    }
}
