package me.hyperperform.forecasting;

import me.hyperperform.forecasting.request.*;
import me.hyperperform.forecasting.response.*;

/**
 * The interface for the forecasting module.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/09/29
 */

public interface IForecasting
{
    GetIntegrationsResponse getIntegrations(GetIntegrationsRequest getIntegrationsRequest);
    UpdateIntegrationResponse updateIntegration(UpdateIntegrationRequest updateIntegrationRequest);
    AddIntegrationResponse addIntegration(AddIntegrationRequest addIntegrationRequest);
    DeleteIntegrationResponse deleteIntegration(DeleteIntegrationRequest deleteIntegrationRequest);

    GetForecastValueResponse getForecastValue(GetForecastValueRequest getForecastValueRequest);
    GetForecastTimeResponse getForecastTime(GetForecastTimeRequest getForecastTimeRequest);
}
