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
    public void showGoodbyMessage() {
        System.out.println("Bye, see you again Brotherman!\n");
    }

    /**
     * Shows a line to the user
     */
    public void showLine() {
        System.out.println(line);
    }

    /**
     * Read command
     */
    public String readCommand() {
        String userCommand = sc.nextLine();
        return userCommand;
    }

    /**
     * Shows the error message to the user
     */
    public void readWrongValue() {
        System.out.println("Brotherman the value you put in wrong.  Try again.");
    }

    /**
     * Shows the task added to the user
     */
    public void showTaskAdded(TaskList taskList) {
        System.out.println("The task has been added Brotherman \n");
        System.out.println(taskList.list().get(taskList.size() - 1).toString());
        System.out.println("Brotherman you have " + taskList.size() + " tasks in the list!");

    }


    /**
     * Shows the task list to the user
     */
    public void showTaskList(TaskList taskList) {
        System.out.println("Hey Brotherman, these are the tasks on your list!");
        ArrayList<Task> list = taskList.list();
        int start = 1;
        for (Task listItems : list) {
            System.out.println(start + ". " + listItems.toString());
            start++;
        }
    }

    /**
     * Shows the tasks with the specified keyword to the user
     * @param taskList Task list to be searched
     * @param keyword Keyword of the task
     */
    public void showFind(TaskList taskList, String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        ArrayList<Task> list = taskList.getTasksByKeyword(keyword);

        if (list.size() == 0) {
            System.out.println("Brotherman, there are no tasks with that keyword");
        }

        for (Task listItems : list) {
            System.out.println(listItems.toString());
        }
    }
}
