package me.hyperperform.forecasting;

import me.hyperperform.forecasting.request.GetIntegrationsRequest;
import me.hyperperform.forecasting.response.GetIntegrationsResponse;

/**
 * Created by rohan on 2016/10/02.
 */
public interface IForecasting
{
    GetIntegrationsResponse getIntegrations(GetIntegrationsRequest getIntegrationsRequest);
}
