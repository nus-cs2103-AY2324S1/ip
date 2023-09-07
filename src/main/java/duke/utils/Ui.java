package duke.utils;

import java.util.List;

import duke.exceptions.DukeException;

/**
 * User interface for the chatbot.
 */
public class Ui {

    private String greeting = "Hello! I'm Toothless.\n"
            + "What can I do for you today?\n"
            + "---------------------------------";
    private String farewell = "Goodbye. Hope to see you soon!\n"
            + "---------------------------------";
    private String addTask = "A new task has been added!\n ";

    /**
     * Greets the user.
     */
    public String greet() {
        return greeting;
    }

    /**
     * Bids farewell to the user.
     */
    public String farewell() {
        return farewell;
    }

    /**
     * Displays the type of input error.
     * @param e The exception thrown.
     */
    public String showDukeError(DukeException e) {
        return e.getMessage();
    }

    /**
     * Tells user to input the correct date format.
     */
    public String showDateError() {
        return "Date cannot be recognised :( please input a valid date format yyyy-mm-dd !";
    }

    /**
     * Tells user that there has been an error.
     */
    public String showGeneralError() {
        return "There has been an internal error. Please try again!";
    }

    /**
     * Confirms that a task has been added.
     * @param taskDescription Description of the task added.
     */
    public String showTaskAdded(String taskDescription) {
        return addTask + taskDescription;
    }

    /**
     * Displays a message when there are no tasks in the list.
     */
    public String showNoTasks() {
        return "You have no tasks! Yay :)";
    }

    /**
     * Lists out all the tasks in the list.
     *     0 represents all tasks, other numbers represent matching tasks.
     * @param tasksDescriptions The list of descriptions of the tasks.
     */
    public String showTasks(List<String> tasksDescriptions, int type) {
        String output = "";
        if (tasksDescriptions.isEmpty()) {
            return showNoTasks();
        } else {
            if (type == 0) {
                output = "Here's your list of tasks!\n";
            } else {
                output = "Here's the matching tasks!\n";
            }
            for (int i = 0; i < tasksDescriptions.size(); i++) {
                String description = (i + 1) + ": " + tasksDescriptions.get(i) + "\n";
                output += description;
            }
            return output;
        }
    }

    /**
     * Confirms that the status of a task has changed.
     * @param status The current status of the task.
     */
    public String showStatusChanged(String status) {
        return status;
    }

    /**
     * Separates the commands and output.
     */
    public void separator() {
        System.out.println("---------------------------------");
    }

}
