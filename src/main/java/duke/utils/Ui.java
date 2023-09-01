package duke.utils;

import duke.exceptions.DukeException;

import java.util.List;

/**
 * User interface for the chatbot.
 */
public class Ui {

    private String greeting = "Hello! I'm Toothless.\n" +
            "What can I do for you today?\n" +
            "---------------------------------";
    private String farewell = "Goodbye. Hope to see you soon!\n" +
            "---------------------------------";
    private String addTask = "A new task has been added!\n ";

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println(greeting);
    }

    /**
     * Bids farewell to the user.
     */
    public void farewell() {
        System.out.println(farewell);
    }

    /**
     * Displays the type of input error.
     * @param e The exception thrown.
     */
    public void showDukeError(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Tells user to input the correct date format.
     */
    public void showDateError() {
        System.out.println("Date cannot be recognised :( please input a valid date format yyyy-mm-dd !");
    }

    /**
     * Tells user that there has been an error.
     */
    public void showGeneralError() {
        System.out.println("There has been an internal error. Please try again!");
    }

    /**
     * Confirms that a task has been added.
     * @param taskDescription Description of the task added.
     */
    public void showTaskAdded(String taskDescription) {
        System.out.println(addTask + taskDescription);
    }

    /**
     * Displays a message when there are no tasks in the list.
     */
    public void showNoTasks() {
        System.out.println("You have no tasks! Yay :)");
    }

    /**
     * Lists out all the tasks in the list.
     *     0 represents all tasks, other numbers represent matching tasks.
     * @param tasksDescriptions The list of descriptions of the tasks.
     */
    public void showTasks(List<String> tasksDescriptions, int type) {
        if (tasksDescriptions.isEmpty()) {
            showNoTasks();
        } else {
            if (type == 0) {
                System.out.println("Here's your list of tasks!\n");
            } else {
                System.out.println("Here's the matching tasks!\n");
            }
            for (int i = 0; i < tasksDescriptions.size(); i++) {
                System.out.println((i + 1) + ": " + tasksDescriptions.get(i));
            }
        }
    }

    /**
     * Confirms that the status of a task has changed.
     * @param status The current status of the task.
     */
    public void showStatusChanged(String status) {
        System.out.println(status);
    }

    /**
     * Separates the commands and output.
     */
    public void separator() {
        System.out.println("---------------------------------");
    }

}
