package duke.ui;

import java.util.List;

import duke.task.Task;

/**
 * Represents the user interface of the program.
 */
public class Ui {

    private final String name;
    public Ui(String name) {
        this.name = name;
    }

    /**
     * Prints the welcome message when the program starts.
     */
    public String getHelloMessage() {
        StringBuilder output = new StringBuilder();
        output.append(getDottedLine());
        output.append("Hello! I'm " + this.name + "\n");
        output.append("What can I do for you?\n");
        output.append(getDottedLine());
        return output.toString();
    }

    /**
     * Prints dotted line with new line.
     */
    public static String getDottedLine() {
        return "________________________\n";
    }

    /**
     * Prints the goodbye message.
     * @return the goodbye message
     */
    public static String getGoodbyeMessage() {
        StringBuilder output = new StringBuilder();
        output.append(getDottedLine());
        output.append("Bye. Hope to see you again soon!\n");
        output.append(getDottedLine());
        return output.toString();
    }

    /**
     * Prints the delete message.
     * @param tasks the list of tasks
     * @param index the index of the task to be deleted
     * @return the delete message
     */
    public static String getDeleteTaskMessage(List<Task> tasks, int index) {
        StringBuilder output = new StringBuilder();
        output.append(getDottedLine());
        output.append("Noted. I've removed this duke.task:\n");
        output.append(tasks.get(index - 1) + "\n");
        String placeholder = tasks.size() == 1 ? "task" : "tasks";
        int remainingTasks = tasks.size() - 1;
        output.append("Now you have " + remainingTasks + " " + placeholder + " in the list.\n");
        output.append(getDottedLine());
        return output.toString();
    }

    /**
     * Prints the add task message.
     * @param tasks the list of tasks
     * @return the add task message
     */
    public static String getAddTaskMessage(List<Task> tasks) {
        StringBuilder output = new StringBuilder();
        output.append(getDottedLine());
        output.append("Got it. I've added this duke.task:\n");
        output.append(tasks.get(tasks.size() - 1) + "\n");
        String placeholder = tasks.size() == 1 ? "task" : "tasks";
        output.append("Now you have " + tasks.size() + " " + placeholder + " in the list.\n");
        output.append(getDottedLine());
        return output.toString();
    }

    /**
     * Prints the list tasks message.
     * @param tasks the list of tasks
     * @return the list tasks message
     */
    public static String getListTasksMessage(List<Task> tasks) {
        StringBuilder output = new StringBuilder();
        output.append(getDottedLine());
        if (tasks.isEmpty()) {
            output.append("There are no tasks in your list.\n");
        } else {
            output.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                output.append((i + 1) + "." + tasks.get(i) + "\n");
            }
        }
        output.append(getDottedLine());
        return output.toString();
    }

    /**
     * Prints the done task message.
     * @param tasks the list of tasks
     * @param index the index of the completed task
     * @return the done task message
     */
    public static String getMarkAsDoneMessage(List<Task> tasks, int index) {
        StringBuilder output = new StringBuilder();
        output.append(getDottedLine());
        output.append("Nice! I've marked this task as done:\n");
        tasks.get(index - 1).markAsDone();
        output.append(tasks.get(index - 1) + "\n");
        output.append(getDottedLine());
        return output.toString();
    }

    /**
     * Prints the undone task message.
     * @param tasks the list of tasks
     * @param index the index of the uncompleted task
     * @return the undone task message
     */
    public static String getMarkAsUndoneMessage(List<Task> tasks, int index) {
        StringBuilder output = new StringBuilder();
        output.append(getDottedLine());
        output.append("OK, I've marked this task as not done yet:\n");
        tasks.get(index - 1).markAsUndone();
        output.append(tasks.get(index - 1) + "\n");
        output.append(getDottedLine());
        return output.toString();
    }

    /**
     * Shows the error message when saving file.
     * @return the error message when saving file
     */
    public static String getErrorSavingToFileMessage() {
        return "Error saving data to file.";
    }

    /**
     * Shows the error message when loading file.
     * @return the error message when loading file
     */
    public static String getErrorLoadingFromFileMessage() {
        return "Error loading data from file.";
    }


}
