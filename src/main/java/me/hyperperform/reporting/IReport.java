package me.hyperperform.reporting;

import me.hyperperform.reporting.request.*;
import me.hyperperform.reporting.response.*;

/**
 * Created by rohan on 2016/08/18.
 */
public interface IReport {
    GetSummaryResponse getSummary(GetSummaryRequest getSummaryRequest);
    GetDetailsResponse getDetails(GetDetailsRequest getDetailsRequest);
    GetScoreResponse getScore(GetScoreRequest getScoreRequest);
}
