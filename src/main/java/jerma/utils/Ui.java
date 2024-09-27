package jerma.utils;

import jerma.tasks.Task;

/**
 * Ui class, handles the bot's response generation.
 */
public class Ui {

    /**
     * Prints and returns hello message
     *
     * @return Hello message
     */
    public String hello() {
        String output = "Hello! I'm Jerma.";
        System.out.println(output);
        return output;
    }

    /**
     * Prints and returns bye message
     *
     * @return Bye message
     */
    public String bye() {
        String output = "See ya soon!";
        System.out.println();
        return output;
    }

    /**
     * Prints and returns error message
     *
     * @param message Error message
     * @return Error message
     */
    public String error(String message) {
        String output = "Error: " + message;
        System.out.println(output);
        return output;
    }

    /**
     * Prints and returns tasklist message
     *
     * @param tasks Tasklist
     * @return Tasklist message
     */
    public String listTasks(TaskList tasks) {
        System.out.println(tasks);
        return tasks.toString();
    }

    /**
     * Prints and returns task message
     *
     * @param task Task
     * @return Task message
     */
    public String newTask(Task task) {
        String type = task.getClass().getName().split("\\.")[1].toLowerCase();
        String output = String.format("Added %s: %s", type, task);
        System.out.println(output);
        return output;
    }

    /**
     * Prints and returns mark task message
     *
     * @param task Marked task
     * @return Mark task message
     */
    public String markTask(Task task) {
        String output = "Marked as done: \n" + task;
        System.out.println(output);
        return output;
    }

    /**
     * Prints and returns unmark task message
     *
     * @param task Unmarked task
     * @return Unmark task message
     */
    public String unmarkTask(Task task) {
        String output = "Marked as undone: \n" + task;
        System.out.println(output);
        return output;
    }

    /**
     * Prints and returns delete task message
     *
     * @param task                   Deleted task
     * @param numberOfTasksRemaining Number of tasks in the list remaining
     * @return Delete task message
     */
    public String deleteTask(Task task, int numberOfTasksRemaining) {
        String output = String.format(
                "Removed the task: \n%s \nYou have %d tasks remaining.", task,
                numberOfTasksRemaining);
        System.out.println(output);
        return output;
    }

    /**
     * Prints and returns help message
     *
     * @return Help message
     */
    public String help() {
        String output = "Command list: \n" + "todo [description] \n"
                + "deadline [description] /by [deadline] \n"
                + "event [description] /from [start] /to [end] \n" + "list \n"
                + "mark [index] \n" + "unmark [index] \n" + "delete [index] \n"
                + "find [keyword] \n" + "bye";
        System.out.println(output);
        return output;
    }
}
