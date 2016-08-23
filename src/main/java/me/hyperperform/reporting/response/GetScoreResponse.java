package me.hyperperform.reporting.response;

/**
 * Created by rohan on 2016/08/19.
 */
public class GetScoreResponse {

    private double score;

    public GetScoreResponse(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
