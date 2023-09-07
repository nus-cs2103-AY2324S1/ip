package duke;

import duke.taskclasses.TaskList;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private MainWindow mainWindow;
    /**
     * Constructs a new Ui instance and displays the welcome message.
     */
    public Ui(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        String logo = " ██▄   ████▄    ▄     ▄▀  \n"
                + "█  █  █   █     █  ▄▀    \n"
                + "█   █ █   █ ██   █ █ ▀▄  \n"
                + "█  █  ▀████ █ █  █ █   █ \n"
                + "███▀        █  █ █  ███  \n"
                + "            █   ██       \n"
                + "                         ";
        mainWindow.printDukeReply("Hello from\n" + logo);
        mainWindow.printDukeReply("What can I do for you?\n");
    }

    /**
     * Prints a message to inform that the task has been marked as done.
     *
     * @param taskContent The content of the task that has been marked as done.
     */
    public String[] printTaskMarkAsDone(String taskContent) {
        String[] res = { "Nice! I've marked this task as done:", "  " + taskContent};
        return res;
    }

    /**
     * Prints a message to inform that the task has been marked as not done.
     *
     * @param taskContent The content of the task that has been marked as not done.
     */
    public String[] printTaskMarkAsNotDone(String taskContent) {
        String[] res = {"OK, I've marked this task as not done yet:", "  " + taskContent};
        return res;
    }

    /**
     * Informs the user that there was an error loading data from storage.
     */
    public String[] showLoadingError() {
        String[] res = {"ERROR reading the file, might be corrupted"};
        return res;
    }

    /**
     * Prints all the tasks available in the provided task list.
     *
     * @param tasks The task list containing tasks to print.
     */
    public String[] returnList(TaskList tasks) {
        String[] res = {"Here are the tasks in your list:",
            tasks.allStatusAndDescriptionToString()};
        return res;
    }

    /**
     * Prints a dashed line for visual separation in the interface.
     */
    public void newDashedLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Informs the user that a task has been deleted.
     *
     * @param content The content of the deleted task.
     */
    public String[] deleteTask(String content) {
        String[] res = {"Noted. I've removed this task:", content};
        return res;
    }

    /**
     * Informs the user about the current count of tasks.
     *
     * @param count The current number of tasks.
     */
    public String[] printTaskCount(int count) {
        String[] res = {String.format("Now you have %s tasks in the list.", count)};
        return res;
    }

    /**
     * Prints all the tasks available in the provided task list that contains the keyword.
     *
     * @param tasks The task list containing tasks to print.
     */
    public String[] printTaskContainingKeyword(TaskList tasks, String keyword) {
        String[] res = {"Here are the matching tasks in your list:",
            tasks.getAllStatusAndDescriptionWithKeyword(keyword)};
        return res;
    }


    /**
     * Prints a goodbye message.
     */
    public String[] printBye() {
        return new String[]{"Bye. Hope to see you again soon!"};
    }
}
