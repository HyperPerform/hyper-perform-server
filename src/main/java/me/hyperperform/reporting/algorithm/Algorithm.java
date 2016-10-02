package me.hyperperform.reporting.algorithm;

import me.hyperperform.reporting.request.CalculateScoreRequest;
import me.hyperperform.reporting.response.CalculateScoreResponse;

/**
 * Algorithm interface forms part of the Strategy design pattern. The Algorithms are used to calculate performance
 * scores. This interface is the Strategy component of the design pattern, any realizations of this interface with
 * be the ConcreteStrategy.
 *
 * Hyper-perform
 * Group: CodusMaximus
 * Date: 2016/08/19
 * Feature: Algorithm Strategy
 */
public interface Algorithm {

    /**
     * A method that all Concrete Strategies must implement.
     * @param calculateScoreRequest Contains the necessary parameters for score calculations which include the user for
     *                              whom the score is being calculated as well as a time period.
     * @return Response object which contains the score which is calculated using some realization of this interface.
     */
    CalculateScoreResponse calculateScore(CalculateScoreRequest calculateScoreRequest);

}
