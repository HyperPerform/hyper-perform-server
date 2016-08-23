package me.hyperperform.reporting.response;

/**
 * Created by rohan on 2016/08/19.
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
