package me.hyperperform.reporting;

import me.hyperperform.reporting.request.*;
import me.hyperperform.reporting.response.*;

/**
 * Serves as the Context in strategy design pattern. Each report generator is has an Algorithm.
 *
 * Created by rohan on 2016/08/18.
 */
public interface IReport {

    /**
     * Calculates and returns summary details
     */
    GetSummaryResponse getSummary(GetSummaryRequest getSummaryRequest);

    /**
     * Returns more fine grained details for a specified integration. The integration is specified in the
     * request object
     */
    GetDetailsResponse getDetails(GetDetailsRequest getDetailsRequest);

    /**
     * Calculates performace score using an algorithm
     */
    GetScoreResponse getScore(GetScoreRequest getScoreRequest);
}
