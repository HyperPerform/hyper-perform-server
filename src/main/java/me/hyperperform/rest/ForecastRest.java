package me.hyperperform.rest;


import me.hyperperform.forecasting.IForecasting;
import me.hyperperform.forecasting.request.AddIntegrationRequest;
import me.hyperperform.forecasting.request.DeleteIntegrationRequest;
import me.hyperperform.forecasting.request.UpdateIntegrationRequest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * REST wraps the {@see me.hyperperform.forecasting.IForecasting} interface. A concrete realization is injected
 * into the object. The services provided by the {@see me.hyperperform.forecasting.IForecasting} interface are then
 * made available through REST endpoints.
 *
 * @author : CodusMaximus
 * @version : 1.0
 * @since : 2016/09/28
 */
@Path("/forecast")
public class ForecastRest
{
    @Inject
    IForecasting forecasting;

    /**
     * Finds the specific integration name and updates/adds the new data to the database.
     * @param str - The JSON String that is sent from the front-end with data of the updated forecasting values.
     * @return A javax.ws.rs.core.Response object with the status of the response as a reply to the request sent to it.
     */
    @POST
    @Path("/updateIntegration")
    @Produces("application/json")
    public Response updateIntegrations(String str) throws Exception
    {
        UpdateIntegrationRequest update = new UpdateIntegrationRequest(str);
        return Response.status(200).entity(forecasting.updateIntegration(update)).build();
    }

    /**
     * Returns list of all integrations along with positions and their corresponding forecast values.
     * @return A javax.ws.rs.core.Response object with the status of the response as a reply to the request sent to it.
     */
    @POST
    @Path("/getIntegrations")
    @Produces("application/json")
    public Response getIntegrations() throws Exception
    {
        return Response.status(200).entity(forecasting.getIntegrations(null)).build();
    }

    /**
     * Finds the specific integration name and updates/adds the new data to the database.
     * @param deleteIntegrationRequest - JSON object which contains the name of the integration to be deleted.
     * @return A javax.ws.rs.core.Response object with the status of the response as a reply to the request sent to it.
     */
    @POST
    @Path("/deleteIntegration")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteIntegration(DeleteIntegrationRequest deleteIntegrationRequest)
    {
//        DeleteIntegrationRequest deleteIntegrationRequest = new DeleteIntegrationRequest("GitIssues");
        return Response.status(200).entity(forecasting.deleteIntegration(deleteIntegrationRequest)).build();
    }

    /**
     * Finds the specific integration name and updates/adds the new data to the database.
     * @param  str - JSON object which contains necessary data to be added to the forecast data
     * @return A javax.ws.rs.core.Response object with the status of the response as a reply to the request sent to it.
     */
    @POST
    @Path("/addIntegration")
    @Produces("application/json")
    public Response addIntegration(String str)
    {
        AddIntegrationRequest addIntegrationRequest = new AddIntegrationRequest();
        addIntegrationRequest.setData(str);

        return Response.status(200).entity(forecasting.addIntegration(addIntegrationRequest)).build();
    }

    private <T> void log(T t)
    {
        System.out.println(t);
    }
}
