package me.hyperperform.rest;


import me.hyperperform.forecasting.IForecasting;
import me.hyperperform.forecasting.request.UpdateIntegrationRequest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/09/28
 * Feature: Forecasting
 */

@Path("/forecast")
public class ForecastRest
{
    @Inject
    IForecasting forecasting;

    /**
     * Finds the specific integration name and updates/adds the new data to the forecasting.json file.
     //* @param jsonStr - The JSON String that is sent from the front-end with data of the updated forecasting values.
     * @return A javax.ws.rs.core.Response object with the status of the response as a reply to the request sent to it.
     */
    @POST
    @Path("/updateForecasts")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateForecasts(UpdateIntegrationRequest update) throws Exception
    {
//        UpdateIntegrationRequest update = new UpdateIntegrationRequest(jsonStr);
        log("IS HERE");
        return Response.status(200).entity(forecasting.updateIntegration(update)).header("Access-Control-Allow-Origin", "*").build();
    }

//    @GET
    @POST
    @Path("/getIntegrations")
    @Produces("application/json")
    public Response getIntegrations() throws Exception
    {
//        ClassLoader classLoader = getClass().getClassLoader();
//        InputStream inputStream = classLoader.getResourceAsStream("forecasting.json");
//
//        JSONObject forecastObject = (JSONObject)(new JSONParser()).parse(new InputStreamReader(inputStream));
//
//        forecastObject = (JSONObject)forecastObject.get("hpForecast");
//        JSONArray integrations = (JSONArray)forecastObject.get("integrations");
//
//        return Response.status(200).entity(integrations.toString()).build();
//
//        log("-------------------------------------");
//        log(forecasting);
//        log("-------------------------------------");

        return Response.status(200).entity(forecasting.getIntegrations(null)).build();
    }

    private <T> void log(T t)
    {
        System.out.println(t);
    }
}
