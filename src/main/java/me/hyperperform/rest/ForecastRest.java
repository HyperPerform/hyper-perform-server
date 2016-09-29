package me.hyperperform.rest;


import org.apache.camel.Produce;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/09/28
 * Feature: Forecasting
 */

@Path("/forecast")
public class ForecastRest
{
    /**
     * Finds the specific integration name and updates/adds the new data to the forecasting.json file.
     * @param jsonStr - The JSON String that is sent from the front-end with data of the updated forecasting values.
     * @return A javax.ws.rs.core.Response object with the status of the response as a reply to the request sent to it.
     */
    @POST
    @Path("/updateForecasts")
    @Consumes("application/json")
    public Response updateForecasts(String jsonStr)
    {
        try
        {
            System.out.println(jsonStr);
            JSONParser j = new JSONParser();

            JSONObject json = (JSONObject)j.parse(jsonStr);
//            System.out.println(json.toString());
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("forecasting.json").getFile());

            JSONObject fFile = (JSONObject)j.parse(new FileReader(file));

            JSONObject hpForecast = (JSONObject)fFile.get("hpForecast");
            JSONArray integrations = (JSONArray)hpForecast.get("integrations");
            JSONObject att, jsonAtt;

            for(int i = 0; i < integrations.size(); i++)
            {
                att = (JSONObject)((JSONObject)integrations.get(i)).get("@attributes");
                jsonAtt = (JSONObject)((JSONObject)integrations.get(i)).get("@attributes");

//                System.out.println(((JSONObject)integrations.get(i)).get("@attributes").equals(jsonAtt.get("name")));

                if(((JSONObject)integrations.get(i)).get("@attributes").equals(jsonAtt.get("name")))
                {
                    integrations.set(i, json);
                }
            }

            return Response.status(200).entity("Successfully updated the forecasting").header("Access-Control-Allow-Origin", "*").build();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return Response.status(400).header("Access-Control-Allow-Origin", "*").build();
    }

//    @GET
    @POST
    @Path("/getIntegrations")
    @Produces("application/json")
    public Response getIntegrations() throws Exception
    {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("forecasting.json");

        JSONObject forecastObject = (JSONObject)(new JSONParser()).parse(new InputStreamReader(inputStream));

        forecastObject = (JSONObject)forecastObject.get("hpForecast");
        JSONArray integrations = (JSONArray)forecastObject.get("integrations");

        return Response.status(200).entity(integrations.toString()).build();
    }

    private <T> void log(T t)
    {
        System.out.println(t);
    }
}
