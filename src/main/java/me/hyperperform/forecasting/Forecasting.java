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
}
