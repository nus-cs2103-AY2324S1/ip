package boti.ui;

import boti.task.Task;
import boti.task.TaskList;

/**
 * Sample class for UI
 */
public class UiSample extends Ui {
    private int printTasksCount = 0;
    private int printExceptionCount = 0;
    private int printAddTaskCount = 0;

    /**
     * Prints the list of tasks (Mock)
     *
     * @param tasks the list of tasks
     */
    @Override
    public String printTasks(TaskList tasks) {
        printTasksCount++;
        return super.printTasks(tasks);
    }

    @Override
    public String printAddTask(Task task, int size) {
        printAddTaskCount++;
        return super.printAddTask(task, size);
    }

    /**
     * Prints the exception (Mock)
     *
     * @param e the exception
     */
    @Override
    public String printException(Exception e) {
        printExceptionCount++;
        return super.printException(e);
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

    /**
     * Returns the number of print add task called
     *
     * @return the number of print add task called
     */
    public int getPrintAddTaskCount() {
        return printAddTaskCount;
    }
}
