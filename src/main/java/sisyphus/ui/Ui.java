package sisyphus.ui;

import java.util.Scanner;

import sisyphus.task.TaskList;

/**
 * class for UI to print pre-set messages.
 */
public class Ui {

    /** Name of the chatbot */
    private static final String NAME = "Sisyphus";
    /** Horizontal Line string */
    private static final String HORIZONTAL_LINE = "_________________________________ \n";

    private Scanner scanner;

    /**
     * Constructor to initialise a scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line by scanner.
     *
     * @return line read by scanner
     */
    public String readLine() {
        return scanner.nextLine();
    }

    /**
     * Greets user with name.
     */
    public static String greet() {
        return "Hello, I'm " + NAME + ". What can I do for you? Do you want to roll a boulder?";
    }

    /**
     * Closes scanner.
     */
    public void exit() {
        scanner.close();
    }

    /**
     * Return string of all the tasks in the given TaskList in a numbered list.
     *
     * @param taskList
     * @return output string
     */
    public static String printTasks(TaskList taskList) {
        String output = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            output += i + 1 + ". " + taskList.getTask(i) + '\n';
        }
        return output;
    }

    /**
     * Return string of tasks with the matching keyword.
     *
     * @param matchingTaskList
     * @param keyword
     * @return output string
     */
    public static String printMatchingTasks(TaskList matchingTaskList, String keyword) {
        String output;
        output = "Below is the list of tasks with keyword - \"" + keyword + "\" :";
        for (int i = 0; i < matchingTaskList.getSize(); i++) {
            output += i + 1 + ". " + matchingTaskList.getTask(i) + '\n';
        }
        output += HORIZONTAL_LINE + '\n';
        return output;
    }


    /**
     * Return string of the marked task and the corresponding message.
     *
     * @param taskList
     * @param index
     * @return output string
     */
    public static String printMarkTask(TaskList taskList, int index) {
        String output;
        output = "The following item has been marked as done. \n";
        output += taskList.getTask(index);
        return output;
    }


    /**
     * Return string of the unmarked task and the corresponding message.
     *
     * @param taskList
     * @param index
     * @return output string
     */
    public static String printUnmarkTask(TaskList taskList, int index) {
        String output;
        output = "The following item has been unmarked and is now uncompleted. \n";
        output += taskList.getTask(index);
        return output;
    }

    /**
     * Return string of the task to be deleted and the corresponding message.
     *
     * @param taskList
     * @param index
     * @return output string
     */
    public static String printDeleteTask(TaskList taskList, int index) {
        String output;
        output = "The following item has been deleted from the list. \n";
        output += taskList.getTask(index);
        return output;
    }

    /**
     * Return string of the most recently added ToDo and a corresponding message.
     *
     * @param taskList
     * @return output string
     */
    public static String printAddTodo(TaskList taskList) {
        String output;
        output = "The following ToDo has been added. \n";
        output += taskList.getLastTask();
        output += "\nYou now have " + taskList.getSize() + " items in the list. \n";
        return output;
    }


    /**
     * Return string of the most recent added deadline and a corresponding message.
     *
     * @param taskList
     * @return output string
     */
    public static String printAddDeadline(TaskList taskList) {
        String output;
        output = "The following deadline has been added. \n";
        output += taskList.getLastTask();
        output += "\nYou now have " + taskList.getSize() + " items in the list.";
        return output;
    }

    /**
     * Return string of the most recent added event and a corresponding message.
     *
     * @param taskList
     * @return output string
     */
    public static String printAddEvent(TaskList taskList) {
        String output;
        output = "The following event has been added. \n";
        output += taskList.getLastTask();
        output += "\nYou now have " + taskList.getSize() + " items in the list.";
        return output;
    }

}
