package me.hyperperform.forecasting;

import me.hyperperform.forecasting.request.*;
import me.hyperperform.forecasting.response.*;

/**
 * Created by rohan on 2016/10/02.
 */
public interface IForecasting
{
    GetIntegrationsResponse getIntegrations(GetIntegrationsRequest getIntegrationsRequest);
    UpdateIntegrationResponse updateIntegration(UpdateIntegrationRequest updateIntegrationRequest);
    AddIntegrationResponse addIntegration(AddIntegrationRequest addIntegrationRequest);
    DeleteIntegrationResponse deleteIntegration(DeleteIntegrationRequest deleteIntegrationRequest);
    GetForecastValueResponse getForecastValue(GetForecastValueRequest getForecastValueRequest);
}
