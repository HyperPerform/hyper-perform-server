package me.hyperperform.reporting.response;

/**
 * Response object for CalculateScore method found in {@see me.hyperperform.reporting.algorithm.Algorithm}.
 *
 * @author : CodusMaximus
 * @version : 1.0
 * @since : 2016/08/19
 */
public class CalculateScoreResponse {

    private double score;

    public CalculateScoreResponse(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
