package me.hyperperform.forecasting;

import me.hyperperform.forecasting.request.AddIntegrationRequest;
import me.hyperperform.forecasting.request.GetIntegrationsRequest;
import me.hyperperform.forecasting.request.UpdateIntegrationRequest;
import me.hyperperform.forecasting.response.AddIntegrationResponse;
import me.hyperperform.forecasting.response.GetIntegrationsResponse;
import me.hyperperform.forecasting.response.UpdateIntegrationResponse;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
}
