package duke;

import task.Task;

/**
 * UI represents the User Interface of the chatbot and deals with interactions with the user.
 */
public class Ui {
    private String input;

    /**
     * The constructor of Ui.
     */
    public Ui() { }

    /**
     * To read the user's input into the chatbot.
     *
     * @param input The input of the user.
     */
    public void readCommand(String input) {
        this.input = input;
    }

    /**
     * A getter function to get the user's input to the chatbot.
     *
     * @return The user's input to the chatbot.
     */
    public String getInput() {
        return this.input;
    }

    /**
     * Prints out the welcome message of the chatbot.
     *
     * @return The String representation of the message to be shown when the user
     *     starts the chatbot.
     */
    public String printWelcome() {
        String msg = "\nHi Traveller! I'm Paimon!"
                + "\nWhat can I do for you?";
        return msg;
    }

    /**
     *  Prints out the goodbye message when the user exits the chatbot.
     *
     * @return The String representation of the message to be shown when the user
     *     exits the chatbot.
     */
    public String printGoodbye() {
        return "Bye Bye Traveller! See you soon :D";
    }

    /**
     * Prints out the message to see the tasks in the user's task list.
     *
     * @param taskList The user's task list.
     * @return The String representation of the message to be shown to list out
     *     the tasks in the task list.
     */
    public String printTaskList(TaskList taskList) {
        return taskList.listTasks();
    }

    /**
     * Prints out the message when a task that is marked as done.
     *
     * @param taskList The user's list of tasks.
     * @param num The index of the task to be marked.
     * @return The String representation of the message to be shown when
     *     a task is marked as done.
     */
    public String printMarkedTask(TaskList taskList, int num) {
        return taskList.markTask(num);
    }

    /**
     * Prints out the message when a task ss marked as undone.
     *
     * @param taskList The user's task list.
     * @param num The index of the task to be unmarked.
     * @return The String representation of the message to be shown when
     *     a task is marked as undone.
     */
    public String printUnmarkedTask(TaskList taskList, int num) {
        return taskList.unmarkTask(num);
    }

    /**
     * Prints out the message when a task is added.
     * @param taskList The user's task list.
     * @param task The task to be added into the task list.
     * @return The String representation of the message to be shown when
     *     the task is added.
     */
    public String printAddTask(TaskList taskList, Task task) {
        return taskList.addTask(task);
    }

    /**
     * Prints out the message when a task is deleted.
     *
     * @param taskList The user's task list.
     * @param num The index of the task to be deleted.
     * @return The String representation of the message to be shown when
     *     a task is deleted.
     */
    public String printDeleteTask(TaskList taskList, int num) {
        return taskList.deleteTask(num);
    }

    /**
     * Prints out the message when the tasks with the specific keyword is
     * requested to be found.
     *
     * @param taskList The user's task list.
     * @param keyword The keyword the user wants to search for in the tasks.
     * @return The String representation of the message to be shown when a keyword is
     *     requested to be found.
     */
    public String printFindTask(TaskList taskList, String keyword) {
        return taskList.findTask(keyword);
    }
}
