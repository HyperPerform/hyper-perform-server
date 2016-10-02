package me.hyperperform.forecasting;

import me.hyperperform.forecasting.request.AddIntegrationRequest;
import me.hyperperform.forecasting.request.DeleteIntegrationRequest;
import me.hyperperform.forecasting.request.GetIntegrationsRequest;
import me.hyperperform.forecasting.request.UpdateIntegrationRequest;
import me.hyperperform.forecasting.response.AddIntegrationResponse;
import me.hyperperform.forecasting.response.DeleteIntegrationResponse;
import me.hyperperform.forecasting.response.GetIntegrationsResponse;
import me.hyperperform.forecasting.response.UpdateIntegrationResponse;

/**
 * Created by rohan on 2016/10/02.
 */
public interface IForecasting
{
    GetIntegrationsResponse getIntegrations(GetIntegrationsRequest getIntegrationsRequest);
    UpdateIntegrationResponse updateIntegration(UpdateIntegrationRequest updateIntegrationRequest);
    AddIntegrationResponse addIntegration(AddIntegrationRequest addIntegrationRequest);
    DeleteIntegrationResponse deleteIntegration(DeleteIntegrationRequest deleteIntegrationRequest);
}
