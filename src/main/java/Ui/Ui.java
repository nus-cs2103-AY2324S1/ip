package Ui;

import DukeException.DukeException;
import List.TaskList;
import Tasks.Task;

import java.util.Scanner;

/**
 * A class that is used to show the user inferfaces.
 */
public class Ui {

    private Scanner scanner;
    final String UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    final String NAME = "CathyTheChattyCat";
    String lineBreak = "\n__________________________________________\n";

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
    public void showError(String e) {
        System.out.println(e);
    }

    /**
     * A method that will show task being added.
     *
     * @param task the task to add.
     * @param userList the TaskList to add to.
     */
    public void showTaskAdded(Task task, TaskList userList) {
        System.out.println("Got it. I've added this task: \n" + task);
        int size = userList.size();
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * A method that will greet the user at the start.
     */
    public void showGreetings() {
        System.out.println(lineBreak + "Hello! I'm " + NAME);
        System.out.printf("What can I do for you?" + lineBreak);
    }

    /**
     * A method that will show that a task is marked.
     *
     * @param task the task being marked.
     */
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done: \n\t" + task);
    }

    /**
     * A method that will show that a task is unmarked.
     *
     * @param task the task being unmarked.
     */
    public void showUnMarkedTask(Task task) {
        System.out.println("OK, I've unmarked this task as not done yet: \n\t" + task);
    }

    /**
     * A method to show that a task is deleted.
     *
     * @param task task being deleted.
     * @param userList where task is deleted from.
     */
    public void showDeleteTask(Task task, TaskList userList) {
        System.out.println("Noted. I've removed this task: \n" + task);
        System.out.println("Now you have " + userList.size() + " tasks in the list");
    }

    /**
     * A method that shows that the task is cleared.
     */
    public void showClearTask() {
        System.out.println("Noted. I've removed all the tasks.");
    }

    /**
     * A method that will show all the task in the list>
     *
     * @param userList where the task is from.
     */
    public void showList(TaskList userList) {
        //System.out.println(lineBreak);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < userList.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + userList.get(i));
        }
        //System.out.println(lineBreak);
    }

    /**
     * A method to say BYE.
     */
    public void showGoodbye() {
        System.out.print("Bye. Hope to see you again soon!");
    }
    public void closeScanner() {
        scanner.close();
    }
    public void showFoundResults(TaskList foundList) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < foundList.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + foundList.get(i));
        }
    }
}
