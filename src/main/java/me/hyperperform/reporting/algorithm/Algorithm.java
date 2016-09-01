package me.hyperperform.reporting.algorithm;

import me.hyperperform.reporting.request.CalculateScoreRequest;
import me.hyperperform.reporting.response.CalculateScoreResponse;

/**
 * Hyper-perform
 * Group: CodusMaximus
 * Date: 2016/08/19
 * Feature: Algorithm Strategy
 *
 * Algorithm interface for Strategy design pattern. The Algorithms are used to calculate performance scores.
 */
public interface Algorithm {

    /**
     * A method that all Concrete Strategies must implement.
     * @param calculateScoreRequest Contains the necessary parameters for score calculations.
     * @return Response object which contains the score.
     */
    CalculateScoreResponse calculateScore(CalculateScoreRequest calculateScoreRequest);

}
