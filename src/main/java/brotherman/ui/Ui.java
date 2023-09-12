package brotherman.ui;

import java.util.ArrayList;
import java.util.Scanner;

import brotherman.tasks.Task;
import brotherman.tasks.TaskList;


/**
 * Represents a user interface to interact with the user
 */
public class Ui {

    /**
     * Scanner to read user input
     */
    private Scanner sc;
    private final String line = "___________________________________________________________\n";
    /**
     * Constructor for Ui
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }


    /**
     * Shows the welcome message to the user
     */
    public void showWelcomeMessage() {
        System.out.println(
                line
                + "Hello! I'm Brotherman\n"
                + "What can I do for you?\n"
                + "Do remember to add your date and time in DD/MM/YYYY\n");
    }


    /**
     * Shows the goodbye message to the user
     */
    public String showGoodbyMessage() {

        return "Bye, see you again Brotherman!\n";
    }

    /**
     * Shows a line to the user
     */
    public String showLine() {
        return line;
    }
    /**
     * Shows the error message to the user
     */
    public String readWrongValue() {
        return "Brotherman the value you put in wrong.  Try again.";
    }

    /**
     * Shows the task added to the user
     */
    public String showTaskAdded(TaskList taskList) {
        String taskAdded = "The task has been added Brotherman \n"
                + taskList.list().get(taskList.size() - 1).toString()
                + "\nBrotherman you have "
                + taskList.size()
                + " tasks in the list!";
        return taskAdded;
    }


    /**
     * Shows the task list to the user
     */
    public String showTaskList(TaskList taskList) {
        String taskListString = "Hey Brotherman, these are the tasks on your list!\n";
        ArrayList<Task> list = taskList.list();
        int start = 1;
        for (Task listItems : list) {
            taskListString += String.format("%d. %s\n", start, listItems.toString());
            start++;
        }
        return taskListString;
    }

    /**
     * Shows the tasks with the specified keyword to the user
     * @param taskList Task list to be searched
     * @param keyword Keyword of the task
     */
    public String showFind(TaskList taskList, String keyword) {

        String output = "Here are the matching tasks in your list:\n";
        ArrayList<Task> list = taskList.getTasksByKeyword(keyword);

        if (list.size() == 0) {
            return "Brotherman, there are no tasks with that keyword";
        }

        for (Task listItems : list) {
            output += listItems.toString() + "\n";
        }
        return output;
    }
    /**
     * Shows the error message to the user
     * @param errorMessage Error message to be shown
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
    /**
     * Shows the help message to the user
     */
    public String showHelp() {
        String message = "Hey brotherman!  These are some commands that Brotherman suppoerts! \n"
                + "1. todo (DESCRIPTION)\n"
                + "2. deadline (DESCRIPTION) /by DATE\n"
                + "3. event (DESCRIPTION) /from DATE /to DATE\n"
                + "4. mark (POSITION)\n"
                + "5. unmark (POSITION)\n"
                + "6. delete (POSITION)\n";
        return message;
    }
}
