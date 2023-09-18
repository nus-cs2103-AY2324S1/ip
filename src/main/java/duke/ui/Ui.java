package duke.ui;

import duke.task.Task;

import java.util.List;

public interface Ui {
    void init();

    /**
     * Prompts user and gets user input.
     * @return String user input.
     */
    String getInput();

    /**
     * Prints exception to user.
     * @param e Exception to display.
     */
    void printException(Exception e);

    /**
     * Prints exit message to user.
     */
    void exit();

    /**
     * Prints add task message to user.
     * @param task duke.Task added.
     */
    void addTask(Task task);

    /**
     * Prints delete task message to user.
     * @param task duke.Task deleted.
     */
    void deleteTask(Task task);

    /**
     * Prints mark task message to user.
     * @param task duke.Task marked.
     */
    void markTask(Task task);

    /**
     * Prints unmark task message to user.
     * @param task duke.Task unmarked.
     */
    void unmarkTask(Task task);

    /**
     * Lists all current tasks to user.
     * @param tasks Tasks to list.
     */
    void listTasks(List<Task> tasks);

    /**
     * Prints invalid command message.
     * @param command Command used.
     */
    void invalidCommand(String command);
}
