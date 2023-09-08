package ui;

import java.util.Scanner;

import list.TaskList;
import tasks.Task;


/**
 * A class that is used to show the user inferfaces.
 */
public class Ui {

    private Scanner scanner;
    //private final String UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private final String NAME = "CathyTheChattyCat";
    private String lineBreak = "\n________________________________________\n";

    /**
     * A constructor method to initialise the UI.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * A method to read the user input and trims the spaces at both ends.
     *
     * @return a string of the input.
     */
    public String readCommand() {
        return this.scanner.nextLine().trim();
    }

    /**
     * A method that will print a line.
     */
    public void showLine() {
        System.out.println(lineBreak);
    }
    /*
    public void showLoadingError() {
        System.out.println(lineBreak + "Not detected in data. New List.TaskList created" + lineBreak);
    }
    public void showRequestForTextFile() {
        System.out.println(lineBreak + "Please Input the txt file you wish to access" + lineBreak);
    }
    public void showUnknownCommand() {
        System.out.println(lineBreak + UNKNOWN_COMMAND + lineBreak);
    }
    public void showDateFormatError(DukeException e) {
        System.out.println("☹ OOPS!!! The description of a time must be in yyyy-mm-dd");
    }
    public void showInvalidDescription(String taskType) {
        System.out.println("☹ OOPS!!! The description of a " + taskType + " is invalid.");
    }
     */

    /**
     * A method that will print out the error.
     *
     * @param e the error code produced.
     */
    public String showError(String e) {
        return e;
    }

    /**
     * A method that will show task being added.
     *
     * @param task the task to add.
     * @param userList the TaskList to add to.
     */
    public String showTaskAdded(Task task, TaskList userList) {
        String top = ("Got it. I've added this task: \n" + task);
        int size = userList.size();
        String bottom = ("Now you have " + size + " tasks in the list.");
        return top + "\n" + bottom;
    }

    /**
     * A method that will greet the user at the start.
     */
    public String showGreetings() {
        String top = (lineBreak + "Hello! I'm " + NAME);
        String bottom = ("What can I do for you?" + lineBreak);
        return top + "\n" + bottom;
    }

    /**
     * A method that will show that a task is marked.
     *
     * @param task the task being marked.
     */
    public String showMarkedTask(Task task) {
        return ("Nice! I've marked this task as done: \n\t" + task);
    }

    /**
     * A method that will show that a task is unmarked.
     *
     * @param task the task being unmarked.
     */
    public String showUnMarkedTask(Task task) {
        return ("OK, I've unmarked this task as not done yet: \n\t" + task);
    }

    /**
     * A method to show that a task is deleted.
     *
     * @param task task being deleted.
     * @param userList where task is deleted from.
     */
    public String showDeleteTask(Task task, TaskList userList) {
        String top = ("Noted. I've removed this task: \n" + task);
        String bottom = ("Now you have " + userList.size() + " tasks in the list");
        return top + "\n" + bottom;
    }

    /**
     * A method that shows that the task is cleared.
     */
    public String  showClearTask() {
        return ("Noted. I've removed all the tasks.");
    }

    /**
     * A method that will show all the task in the list>
     *
     * @param userList where the task is from.
     */
    public String showList(TaskList userList) {
        //System.out.println(lineBreak);
        StringBuilder builder = new StringBuilder();
        String top = ("Here are the tasks in your list:");
        for (int i = 0; i < userList.size(); i++) {
            int index = i + 1;
            builder.append(index + "." + userList.get(i) + '\n');
        }
        return top + "\n" + builder;
    }

    /**
     * A method to say BYE.
     */
    public String showGoodbye() {
        return ("Bye. Hope to see you again soon!");
    }
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Shows the result found base on the word given.
     * @param foundList the list of found tasks.
     * @return a string of all the tasks.
     */
    public String showFoundResults(TaskList foundList) {
        StringBuilder builder = new StringBuilder();
        String top = ("Here are the matching tasks in your list:");
        for (int i = 0; i < foundList.size(); i++) {
            int index = i + 1;
            builder.append(index + "." + foundList.get(i) + "\n");
        }
        return top + "\n" + builder;
    }
}
