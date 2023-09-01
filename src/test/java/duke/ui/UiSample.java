package duke.ui;

import duke.task.TaskList;

public class UiSample extends Ui {
    public int printTasksCount = 0;
    public int printExceptionCount = 0;

    /**
     * Prints the list of tasks
     *  
     * @param tasks the list of tasks
     */
    @Override
    public void printTasks(TaskList tasks) {
        printTasksCount++;
    }

    /**
     * Prints the exception
     * 
     * @param e the exception
     */
    @Override
    public void printException(Exception e) {
        printExceptionCount++;
    }
}
