package duke.ui;

import duke.task.TaskList;

/**
 * Sample class for UI
 */
public class UiSample extends Ui {
    private int printTasksCount = 0;
    private int printExceptionCount = 0;

    /**
     * Prints the list of tasks (Mock)
     *
     * @param tasks the list of tasks
     */
    @Override
    public void printTasks(TaskList tasks) {
        printTasksCount++;
    }

    /**
     * Prints the exception (Mock)
     *
     * @param e the exception
     */
    @Override
    public void printException(Exception e) {
        printExceptionCount++;
    }

    /**
     * Returns the number of print tasks called
     *
     * @return the number of print tasks called
     */
    public int getPrintTasksCount() {
        return printTasksCount;
    }

    /**
     * Returns the number of print exception called
     *
     * @return the number of print exception called
     */
    public int getPrintExceptionCount() {
        return printExceptionCount;
    }
}
