package me.hyperperform.forecasting;

import me.hyperperform.forecasting.request.*;
import me.hyperperform.forecasting.response.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.*;

/**
 * A class that realises the {@see me.hyperperform.forecasting.IForecasting} interface. Used to interface with the json string that holds the forecast data.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/09/30
 */

public class Forecasting implements IForecasting
{
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    @PostConstruct
    private void initConnection()
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    @PreDestroy
    private void disconnect()
    {
        entityManager.close();
        entityManagerFactory.close();
    }

    /**
     * A method that retrieves all the data from the database and returns an object that includes the
     * @param getIntegrationsRequest - Accepts a {@see me.hyperperform.forecasting.request.getIntegrationsRequest}
     * @return GetIntegrationsResponse - An object of type {@see me.hyperperform.forecasting.response.GetIntegrationsResponse}.
     */
    public GetIntegrationsResponse getIntegrations(GetIntegrationsRequest getIntegrationsRequest)
    {
        if (entityManager == null)
        {
            entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
        }

        GetIntegrationsResponse getIntegrationsResponse = new GetIntegrationsResponse();

        JSONObject j = getForecastData();
        j = (JSONObject)j.get("hpForecast");
        JSONArray integrations = (JSONArray)j.get("integrations");

        getIntegrationsResponse.setData(integrations.toString());
        getIntegrationsResponse.setSize(integrations.size());

        return getIntegrationsResponse;
    }

    /**
     * A method that changes the data within an integration specified by the data previously mapped onto the parameter object.
     * @param updateIntegrationRequest - Accepts a {@see me.hyperperform.forecasting.request.UpdateIntegrationRequest}
     * @return UpdateIntegrationResponse - An object of type {@see me.hyperperform.forecasting.response.UpdateIntegrationResponse}.
     */
    public UpdateIntegrationResponse updateIntegration(UpdateIntegrationRequest updateIntegrationRequest)
    {
        if (entityManager == null)
        {
            entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
        }

        UpdateIntegrationResponse updateIntegrationResponse = new UpdateIntegrationResponse();

        try
        {
            JSONObject json = (JSONObject) new JSONParser().parse(updateIntegrationRequest.getData());
            JSONObject fFile = getForecastData();

            JSONObject hpForecast = (JSONObject)fFile.get("hpForecast");
            JSONArray integrations = (JSONArray)hpForecast.get("integrations");

            for (int i = 0; i < integrations.size(); i++)
            {
                JSONObject att = (JSONObject) ((JSONObject) integrations.get(i)).get("@attributes");
                JSONObject jsonAtt = (JSONObject) json.get("@attributes");

                if (att.get("name").equals(jsonAtt.get("name")))
                {
                    integrations.set(i, json);

                    setForecastData(fFile);

                    updateIntegrationResponse.setUpdated(true);
                    return updateIntegrationResponse;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return updateIntegrationResponse;
    }

    /**
     * A method that adds an extra integration to the forecasting, specified by the data previously mapped onto the parameter object.
     * @param addIntegrationRequest - Accepts a {@see me.hyperperform.forecasting.request.AddIntegrationRequest}
     * @return AddIntegrationResponse - An object of type {@see me.hyperperform.forecasting.response.AddIntegrationResponse}.
     */
    public AddIntegrationResponse addIntegration(AddIntegrationRequest addIntegrationRequest)
    {
        if (entityManager == null)
        {
            entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
        }

        AddIntegrationResponse addIntegrationResponse = new AddIntegrationResponse();
        addIntegrationResponse.setAdded(true);

        try
        {
            JSONObject json = getForecastData();
            JSONObject j = (JSONObject) json.get("hpForecast");
            JSONArray integrations = (JSONArray) j.get("integrations");

            JSONObject addJson = (JSONObject) new JSONParser().parse(addIntegrationRequest.getData());
            String addName = (String)(((JSONObject)addJson.get("@attributes")).get("name"));

            int n = integrations.size();
            for (int k = 0; k < n; k++)
            {
                JSONObject curr = (JSONObject) integrations.get(k);
                curr = (JSONObject) curr.get("@attributes");

                String integrationName = (String) curr.get("name");

                if (integrationName.equals(addName))
                {
                    addIntegrationResponse.setAdded(false);
                    return addIntegrationResponse;
                }
            }

            integrations.add(addJson);
            setForecastData(json);
        }

        catch (Exception e)
        {
            e.printStackTrace();
            addIntegrationResponse.setAdded(false);
        }

        return addIntegrationResponse;
    }

    /**
     * A method that deletes an integration from the forecasting, specified by the data previously mapped onto the parameter object.
     * @param deleteIntegrationRequest - Accepts a {@see me.hyperperform.forecasting.request.DeleteIntegrationRequest}
     * @return DeleteIntegrationResponse - An object of type {@see me.hyperperform.forecasting.response.DeleteIntegrationResponse}.
     */
    public DeleteIntegrationResponse deleteIntegration(DeleteIntegrationRequest deleteIntegrationRequest)
    {
        if (entityManager == null)
        {
            entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
        }

        DeleteIntegrationResponse deleteIntegrationResponse = new DeleteIntegrationResponse();
        deleteIntegrationResponse.setDeleted(false);

        JSONObject json = getForecastData();
        JSONObject j = (JSONObject)json.get("hpForecast");
        JSONArray integrations = (JSONArray)j.get("integrations");

        int n = integrations.size();
        for (int k = 0; k < n; k++)
        {
            JSONObject curr = (JSONObject)integrations.get(k);
            curr = (JSONObject)curr.get("@attributes");

            String integrationName = (String)curr.get("name");

            if (integrationName.equals(deleteIntegrationRequest.getIntegrationName())) {
                integrations.remove(k);

                setForecastData(json);

                deleteIntegrationResponse.setDeleted(true);
                return deleteIntegrationResponse;
            }
        }

        return deleteIntegrationResponse;
    }

    /**
     * A method that returns the value for a specified position, previously mapped onto the parameter object, in a specified integration, also previously mapped onto the parameter object.
     * @param getForecastValueRequest - Accepts a {@see me.hyperperform.forecasting.request.GetForecastValueRequest}
     * @return GetForecastValueResponse - An object of type {@see me.hyperperform.forecasting.response.GetForecastValueResponse}.
     */
    public GetForecastValueResponse getForecastValue(GetForecastValueRequest getForecastValueRequest)
    {
        if (entityManager == null)
        {
            entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
        }

        String integration = getForecastValueRequest.getIntegration();
        String position = getForecastValueRequest.getPosition();

        JSONObject j = getForecastData();
        j = (JSONObject)j.get("hpForecast");
        JSONArray integrations = (JSONArray)j.get("integrations");

        for (int k = 0; k < integrations.size(); k++)
        {
            JSONObject curr = (JSONObject)integrations.get(k);
            JSONObject attr = (JSONObject)curr.get("@attributes");

            if (((String)attr.get("name")).equals(integration))
            {
                JSONArray positions = (JSONArray) ((JSONObject)curr.get("positions")).get("position");
                for (int i = 0; i < positions.size(); i++)
                {
                    JSONObject pos = (JSONObject)positions.get(i);

                    if (((String)((JSONObject)pos.get("@attributes")).get("name")).equals(position))
                        return new GetForecastValueResponse(((Long)pos.get("value")).doubleValue());
                }
            }
        }

        return new GetForecastValueResponse(-1);
    }

    /**
     * A method that returns the time for a specified position, previously mapped onto the parameter object, in a specified integration, also previously mapped onto the parameter object.
     * @param getForecastTimeRequest - Accepts a {@see me.hyperperform.forecasting.request.GetForecastTimeRequest}
     * @return GetForecastTimeResponse - An object of type {@see me.hyperperform.forecasting.response.GetForecastTimeResponse}.
     */
    public GetForecastTimeResponse getForecastTime(GetForecastTimeRequest getForecastTimeRequest)
    {
        if (entityManager == null)
        {
            entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
        }

        String integration = getForecastTimeRequest.getIntegration();
        String position = getForecastTimeRequest.getPosition();

        JSONObject j = getForecastData();
        j = (JSONObject)j.get("hpForecast");
        JSONArray integrations = (JSONArray)j.get("integrations");

        for (int k = 0; k < integrations.size(); k++)
        {
            JSONObject curr = (JSONObject)integrations.get(k);
            JSONObject attr = (JSONObject)curr.get("@attributes");

            if (((String)attr.get("name")).equals(integration))
            {
                JSONArray positions = (JSONArray) ((JSONObject)curr.get("positions")).get("position");
                for (int i = 0; i < positions.size(); i++)
                {
                    JSONObject pos = (JSONObject)positions.get(i);

                    if (((String)((JSONObject)pos.get("@attributes")).get("name")).equals(position))
                        return new GetForecastTimeResponse((String)pos.get("time"));
                }
            }
        }

        return new GetForecastTimeResponse(null);
    }

    /**
     * A method that queries the database and returns forecast data from the database.
     * @return JSONObject - A JSON Object containing the full JSON forecast data from the database.
     */
    private JSONObject getForecastData()
    {
        if (entityManager == null)
        {
            entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
        }

        try
        {
            Query q = entityManager.createQuery("select a.data from ForecastData a");
            JSONObject json = (JSONObject) new JSONParser().parse((String) q.getSingleResult());

            return json;
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * A method that updates the database with the data in the parameter.
     * @param json - A JSON Object with the data that will be updated in the database
     */
    private void setForecastData(JSONObject json)
    {

        if (entityManager == null)
        {
            entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
        }

        try
        {
            Query q = entityManager.createQuery("Update ForecastData set data=:jsonData")
                    .setParameter("jsonData", json.toString());

            entityTransaction.begin();
            q.executeUpdate();
            entityTransaction.commit();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private <T> void log(T t)
    {
        System.out.println(t);
    }
}
