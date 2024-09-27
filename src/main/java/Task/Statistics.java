package task;

/**
 * The `Statistics` class represents task statistics, including the number of tasks completed within the last week,
 * the total number of tasks completed, the percentage of tasks completed within the last week, and the percentage of
 * total tasks completed.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class Statistics {
    private int tasksCompletedThisWeek;
    private int totalTasksCompleted;
    private double percentageCompletedThisWeek;
    private double percentageTotalCompleted;

    /**
     * Constructs a new `Statistics` object with default values.
     * Initially, all statistics values are set to zero.
     */
    public Statistics() {
        tasksCompletedThisWeek = 0;
        totalTasksCompleted = 0;
        percentageCompletedThisWeek = 0.0;
        percentageTotalCompleted = 0.0;
    }

    /**
     * Gets the number of tasks completed within the last week.
     *
     * @return The number of tasks completed within the last week.
     */
    public int getTasksCompletedThisWeek() {
        return tasksCompletedThisWeek;
    }

    /**
     * Sets the number of tasks completed within the last week.
     *
     * @param tasksCompletedThisWeek The number of tasks completed within the last week.
     */
    public void setTasksCompletedThisWeek(int tasksCompletedThisWeek) {
        this.tasksCompletedThisWeek = tasksCompletedThisWeek;
    }

    /**
     * Gets the total number of tasks completed.
     *
     * @return The total number of tasks completed.
     */
    public int getTotalTasksCompleted() {
        return totalTasksCompleted;
    }

    /**
     * Sets the total number of tasks completed.
     *
     * @param totalTasksCompleted The total number of tasks completed.
     */
    public void setTotalTasksCompleted(int totalTasksCompleted) {
        this.totalTasksCompleted = totalTasksCompleted;
    }

    /**
     * Gets the percentage of tasks completed within the last week.
     *
     * @return The percentage of tasks completed within the last week.
     */
    public double getPercentageCompletedThisWeek() {
        return percentageCompletedThisWeek;
    }

    /**
     * Sets the percentage of tasks completed within the last week.
     *
     * @param percentCompletedThisWeek The percentage of tasks completed within the last week.
     */
    public void setPercentageCompletedThisWeek(double percentCompletedThisWeek) {
        this.percentageCompletedThisWeek = percentCompletedThisWeek;
    }

    /**
     * Gets the percentage of total tasks completed.
     *
     * @return The percentage of total tasks completed.
     */
    public double getPercentageTotalCompleted() {
        return percentageTotalCompleted;
    }

    /**
     * Sets the percentage of total tasks completed.
     *
     * @param percentTotalCompleted The percentage of total tasks completed.
     */
    public void setPercentageTotalCompleted(double percentTotalCompleted) {
        this.percentageTotalCompleted = percentTotalCompleted;
    }
}
