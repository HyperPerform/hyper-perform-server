package me.hyperperform.reporting.response;

/**
 * Response object for GetScore method found in {@see me.hyperperform.reporting.IReport}.
 *
 * @author : CodusMaximus
 * @version : 1.0
 * @since : 2016/08/19
 */
public class GetScoreResponse {

    private double score;
    private String performance;

    public GetScoreResponse(double score, String performance) {
        this.score = score;
        this.performance = performance;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }
}
