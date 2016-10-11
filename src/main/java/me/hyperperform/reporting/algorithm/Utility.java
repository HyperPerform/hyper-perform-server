package me.hyperperform.reporting.algorithm;

/**
 * Created by rohan on 2016/10/11.
 */
public class Utility
{
    public static double scale(double value, double total, double start, double end)
    {
        value /= total;
        value = (value*(end-start)) + start;

        return (value > end) ? end : value;
    }
}
