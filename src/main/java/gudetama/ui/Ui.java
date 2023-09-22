package gudetama.ui;

import java.util.ArrayList;
import java.util.Scanner;

import gudetama.tasks.Task;
import gudetama.tasks.TaskList;

/**
 * Represents a user interface that interacts with the user
 */
public class Ui {
    /**
     * Scanner that is used to read the input from the user
     */
    private final Scanner scanner;

    /**
     * String contains the help message that contains a list of valid commands
     * that the user can use.
     */
    private final String help = "Here is a list of valid commands: \n" +
            "1. todo \n" +
            "2. event \n" +
            "3. deadline \n" +
            "4. mark \n" +
            "5. unmark \n" +
            "6. list \n" +
            "7. delete \n";

    /**
     * Constructor for Ui
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user
     * @return String containing the welcome message to be displayed to the user
     */
    public String showWelcome() {
        return "Hello! I'm Gudetama.\n"
                + "What can I do for you?\n";
    }

    /**
     * Displays the invalid message to the user
     * @return String containing the invalid message to be displayed to the user
     */
    public String showInvalid(){
        return "Invalid Command. Please Try Again! \n" + help;
    }

    /**
     * Displays a help message which contains a list of valid commands
     * that the user can use in their input.
     * @return String containing the help message to be displayed
     */
    public String showHelp(){
        return help;
    }

    /**
     * Displays the error message to the user.
     * @param message Error message to be displayed to the user
     * @return String containing the provided error message
     */
    public String showErrorMessage(String message){
        return message;
    }

    /**
     * Prints goodbye message
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints out the tasks in the task list.
     * @param
     */
    public String showList(TaskList taskList) {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.len(); i++) {
            result += (i + 1) + ". " + taskList.retrieveList().get(i) + "\n";
        }

        return result;
    }

    /**
     * Prints out the newly added task in the task list
     * @param taskList TaskList containing the list of tasks
     */
    public String showAddedTask(TaskList taskList) {
        String task = "Got it. I've added this task: \n"
                + taskList.retrieveList().get(taskList.len() - 1).toString()
                + "\n Now you have " + taskList.len() + " tasks in the list";
        return task;
    }

    /**
     * Prints out the task that has been removed by the user
     * @param taskList TaskList containing the list of tasks
     * @param num Index of the task to be removed
     */
    public String showRemovedTask(TaskList taskList, int num) {
        int total = taskList.len() - 1;
        String output = "Noted. I've removed this task:\n"
                + taskList.retrieveList().get(num - 1).toString()
                + "\n Now you have " + total + " tasks in the list";

        return output;
    }

    /**
     * Displays the tasks with the keyword that the user inputs
     * @param taskList TaskList containing the list of tasks
     * @param input Keyword that the user inputs
     */
    public String showFindResults(TaskList taskList , String input) {
        ArrayList<Task> list = taskList.getFilteredTasks(input);
        String result = "Here are the matching tasks in your list:\n";

        if (list.size() == 0) {
            return "There are no tasks with that keyword.";
        } else {
            for (int i = 1; i <= list.size(); i++) {
                result += i + ". " + list.get(i - 1).toString() + "\n";
            }

            return result;
        }
    }

}
