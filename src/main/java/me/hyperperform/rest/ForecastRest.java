package me.hyperperform.rest;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@Path("/forecast")
public class ForecastRest
{
    @POST
    @Path("/forecast/updateForecasts")
    @Consumes("application/json")
    public Response updateForecasts(String jsonStr)
    {
        try
        {
            JSONObject json = (JSONObject)new JSONParser().parse(jsonStr);

            JSONObject integration = (JSONObject)json.get("integration");
            JSONArray positions = (JSONArray)json.get("positions");

            String integrationName = (String) integration.get("name");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element integrationType = doc.createElement("integration");
            integrationType.setAttribute("name", integrationName);

            Element positionsArr = doc.createElement("positions");

            Element position, value;

            JSONObject curr;

            for (int i = 0; i < positions.size(); i++)
            {
                position = doc.createElement("position");
                value = doc.createElement("value");
                curr = (JSONObject)positions.get(i);

                position.setAttribute("name", (String)curr.get("name"));
                value.setAttribute("time", (String)curr.get("time"));
                value.appendChild(doc.createTextNode((String)curr.get("value")));

                position.appendChild(value);
                positionsArr.appendChild(position);
            }

            // Still need to access the xml document with JPA to update the document

        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (ParserConfigurationException pse)
        {
            pse.printStackTrace();
        }
        return Response.status(200).entity("Successfully updated the forecasting").header("Access-Control-Allow-Origin", "*").build();
    }
}
