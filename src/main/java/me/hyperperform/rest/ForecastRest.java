package me.hyperperform.rest;


import me.hyperperform.forecasting.IForecasting;
import me.hyperperform.forecasting.request.DeleteIntegrationRequest;
import org.apache.camel.Produce;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileWriter;

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
//            System.out.println(jsonStr);
            JSONParser j = new JSONParser();

            JSONObject json = (JSONObject)j.parse(jsonStr);
//            System.out.println(json.toString());
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("forecasting.json").getFile());

            JSONObject fFile = (JSONObject)j.parse(new FileReader(file));

            JSONObject hpForecast = (JSONObject)fFile.get("hpForecast");
//            System.out.println("fFile 1:\n" + fFile.toJSONString());
            JSONArray integrations = (JSONArray)hpForecast.get("integrations");
            JSONObject att, jsonAtt;
//            System.out.println("Can Write? " + file.canWrite() + "\n filename: " + file.toString());
            FileWriter fw = new FileWriter(file);
//            if(integrations.indexOf("@attributes") != -1 && )
//            {
                for (int i = 0; i < integrations.size(); i++)
                {
                    att = (JSONObject) ((JSONObject) integrations.get(i)).get("@attributes");
                    jsonAtt = (JSONObject) json.get("@attributes");
                    System.out.println(att.get("name") + " " + jsonAtt.get("name"));
                    if (att.get("name").equals(jsonAtt.get("name")))
                    {
                        integrations.set(i, json);
//                        System.out.println("fFile 2:\n" + fFile.toJSONString());
                        fw.write(fFile.toJSONString());
                        fw.flush();
                        fw.close();
                        break;
                    }
                }


//            }
//            else
//            {
//
//            }

            return Response.status(200).entity("Successfully updated the forecasting").header("Access-Control-Allow-Origin", "*").build();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return Response.status(200).entity("Failed to update forecasting").header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Path("/getIntegrations")
    @Produces("application/json")
    public Response getIntegrations() throws Exception
    {
        return Response.status(200).entity(forecasting.getIntegrations(null)).build();
    }

    @POST
    @Path("/deleteIntegration")
    @Produces("application/json")
    public Response deleteIntegration()
    {
        DeleteIntegrationRequest deleteIntegrationRequest = new DeleteIntegrationRequest("GitIssues");
        return Response.status(200).entity(forecasting.deleteIntegration(deleteIntegrationRequest)).build();
    }

    private <T> void log(T t)
    {
        System.out.println(t);
    }
}
