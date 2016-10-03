package me.hyperperform.forecasting;

import me.hyperperform.forecasting.request.AddIntegrationRequest;
import me.hyperperform.forecasting.request.DeleteIntegrationRequest;
import me.hyperperform.forecasting.request.GetIntegrationsRequest;
import me.hyperperform.forecasting.request.UpdateIntegrationRequest;
import me.hyperperform.forecasting.response.AddIntegrationResponse;
import me.hyperperform.forecasting.response.DeleteIntegrationResponse;
import me.hyperperform.forecasting.response.GetIntegrationsResponse;
import me.hyperperform.forecasting.response.UpdateIntegrationResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.*;

/**
 * Created by rohan on 2016/10/02.
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
    
    public GetIntegrationsResponse getIntegrations(GetIntegrationsRequest getIntegrationsRequest)
    {
        GetIntegrationsResponse getIntegrationsResponse = new GetIntegrationsResponse();

        JSONObject j = getForecastData();
        j = (JSONObject)j.get("hpForecast");
        JSONArray integrations = (JSONArray)j.get("integrations");

        getIntegrationsResponse.setData(integrations.toString());
        getIntegrationsResponse.setSize(integrations.size());

        return getIntegrationsResponse;
    }

    public UpdateIntegrationResponse updateIntegration(UpdateIntegrationRequest updateIntegrationRequest)
    {
        UpdateIntegrationResponse updateIntegrationResponse = new UpdateIntegrationResponse();
        String jsonStr = updateIntegrationRequest.getData();
        System.out.println("jsonStr: " + jsonStr);
        try
        {
            System.out.println("jsonStr: " + jsonStr);
            JSONParser j = new JSONParser();

            JSONObject json = (JSONObject)j.parse(jsonStr);
//            System.out.println(json.toString());

            JSONObject fFile = getForecastData();

            JSONObject hpForecast = (JSONObject)fFile.get("hpForecast");
//            System.out.println("fFile 1:\n" + fFile.toJSONString());
            JSONArray integrations = (JSONArray)hpForecast.get("integrations");
            JSONObject att, jsonAtt;
//            System.out.println("Can Write? " + file.canWrite() + "\n filename: " + file.toString());


            for (int i = 0; i < integrations.size(); i++)
            {
                att = (JSONObject) ((JSONObject) integrations.get(i)).get("@attributes");
                jsonAtt = (JSONObject) json.get("@attributes");
                System.out.println(att.get("name") + " " + jsonAtt.get("name"));
                if (att.get("name").equals(jsonAtt.get("name")))
                {
                    integrations.set(i, json);
                    updateIntegrationResponse.setData(fFile.toJSONString());
                    setForecastData(fFile);
//                        System.out.println("fFile 2:\n" + fFile.toJSONString());
                    break;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return updateIntegrationResponse;
    }

    public AddIntegrationResponse addIntegration(AddIntegrationRequest addIntegrationRequest)
    {
        AddIntegrationResponse addIntegrationResponse = new AddIntegrationResponse();

        return addIntegrationResponse;
    }

    public DeleteIntegrationResponse deleteIntegration(DeleteIntegrationRequest deleteIntegrationRequest)
    {
        DeleteIntegrationResponse deleteIntegrationResponse = new DeleteIntegrationResponse();
        deleteIntegrationResponse.setDeleted(false);

        JSONObject j = getForecastData();
        j = (JSONObject)j.get("hpForecast");
        JSONArray integrations = (JSONArray)j.get("integrations");

        int n = integrations.size();
        for (int k = 0; k < n; k++)
        {
            JSONObject curr = (JSONObject)integrations.get(k);
            curr = (JSONObject)curr.get("@attributes");

            String integrationName = (String)curr.get("name");

            if (integrationName.equals(deleteIntegrationRequest.getIntegrationName())) {
                integrations.remove(k);

                setForecastData(j);

                deleteIntegrationResponse.setDeleted(true);
                return deleteIntegrationResponse;
            }
        }

        return deleteIntegrationResponse;
    }

    private JSONObject getForecastData()
    {
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

    private void setForecastData(JSONObject j)
    {
        try
        {
            Query q = entityManager.createQuery("Update ForecastData set data=:jsonData")
                    .setParameter("jsonData", j.toString());

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
