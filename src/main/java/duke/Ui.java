package duke;

import java.util.Scanner;

/**
 * A class that handles all the user interactions of the program
 */
public class Ui {
    private String lnspace = "____________________________________________________________";
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * A method that prints greeting when program starts
     * @return string representing greeting
     */
    public String greeting() {
        String greeting = lnspace + "\n"
                + "Hello! I'm Lorem\n"
                + "What can I do for you?\n"
                + lnspace + "\n";
        System.out.println(greeting);
        return greeting;
    }

    /**
     * A method that prints ending when program terminates
     * @return string representing ending
     */
    public String ending() {
        String ending = "Bye. Hope to see you again soon!\n"
                + lnspace;
        System.out.println(ending);
        this.sc.close();
        return ending;
    }

    /**
     * A method that prints a straight line
     * @return string representing straight line
     */
    public String printLine() {
        System.out.println(lnspace);
        return lnspace;
    }

    /**
     * A method that prints error message when existing data file is not found
     * @return string representing error message
     */
    public String showLoadingError() {
        String out = "Existing data not found. Creating new data file tasks.txt found in ./data/ folder.";
        System.out.println(out);
        return out;
    }

    /**
     * A method that prints the error message specified
     * @param message error message that user wants to print
     * @return string that user inputted as argument
     */
    public String showError(String message) {
        System.out.println(message);
        return message;
    }

    /**
     * A method that reads the next command that user inputted
     * @returns string that user inputted
     */

    public String readCommand() {
        return this.sc.nextLine().trim();
    }

    /**
     * A method that prints message during task addition
     * @param arr TaskList object that task object is added to
     */
    public void addTask(TaskList arr) {
        System.out.println("Got it. I've added this task:");
        System.out.println(arr.taskToString(arr.length() - 1));
        System.out.println("Now you have " + (arr.length()) + arr.numTasksToString() + " in the list.");
    }

    /**
     * A method that prints message during task marking
     * @param index index of Task object that user marked in specified TaskList object
     * @param arr TaskList object that contains Task object that user marked
     */
    public void markTask(int index, TaskList arr) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(arr.taskToString(index));
    }

    /**
     * A method that prints message during task unmarking
     * @param index index of Task object that user unmarked in specified TaskList object
     * @param arr TaskList object that contains Task object that user unmarked
     */
    public void unmarkTask(int index, TaskList arr) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(arr.taskToString(index));
    }

    /**
     * A method that prints message when user wants a list of tasks in TaskList object
     * @param arr TaskList object that contains existing Task objects
     * @return string that is printed representing list of tasks in specified
     * TaskList
     */
    public String list(TaskList arr) {
        String out = "Here are the tasks in your list:\n";
        for (int i = 0; i < arr.length(); i++) {
            out = out + (i + 1) + ". " + arr.taskToString(i) + "\n";
        }
        System.out.println(out);
        return out;
    }

    /**
     * A method that finds all existing tasks that contains specified substring and lists it 
     * @param arr taskList object that contains existing Task objects
     * @param keyString string that user wants to search for
     * @return string that lists all tasks with descriptions containing specified substring
     */
    public String find(TaskList arr, String keyString) {
        String out = "Here are the matching tasks in your list:\n";
        TaskList outTaskList = arr.filter(keyString);
        if (outTaskList.length() == 0) {
            out = "There are no matching tasks in the list.";
            System.out.println(out);
            return out;
        }
        for (int i = 0; i < outTaskList.length(); i++) {
            out = out + (i + 1) + ". " + outTaskList.taskToString(i) + "\n";
        }
        System.out.println(out);
        return out;
    }

    /**
     * A method that prints message when user deletes a task in TaskList
     * @param arr TaskList where deleted Task is contained in
     * @param index index of Task object that was deleted
     */
    public void deleteTask(TaskList arr, int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(arr.taskToString(index));
        System.out.println("Now you have " + (arr.length() - 1) + arr.numTasksToString() + " in the list.");
    }
}
