package taskmate.tools;

import java.util.ArrayList;
import java.util.Scanner;

import taskmate.tools.tasks.Task;

/**
 * The Ui class deals with all interactions with the user. It contains methods to take in inputs from the user, as well
 * as methods to print out replies to the user.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "--------------------";
    private final String chatbotName;
    private final Scanner sc;
    private String storedMessage;

    /**
     * A constructor for Ui objects that takes in the chatbot's name as one of its inputs. It is thus customizable to
     * other chatbots with different names.
     * Note: At initialization, a Scanner object is created to take in user input from System.in.
     * @param chatbotName A String object that represents the name of the chatbot
     */
    public Ui(String chatbotName) {
        this.chatbotName = chatbotName;
        this.sc = new Scanner(System.in);
    }

    /**
     * This method sets `storedMessage` to String `s`. It is only supposed to run after a message is popped using the
     * `popStoredMessage` method, which sets `storedMessage` to null. If this condition is not adhered (ie. running
     * `setStoredMessage` consecutively without running `popStoredMessage`, an AssertionError will be raised.
     * @param s A String object that represents the last-printed item by the Ui object
     */
    public void setStoredMessage(String s) {
        assert this.storedMessage == null;
        this.storedMessage = s;
    }

    /**
     * When the Ui object prints a reply, this reply is set to `storedMessage` via the setStoredMessage function. Since
     * the GUI requires Strings to be passed in order to display them, this method is used to return the last-printed
     * String.
     * After retrieving the last-printed String, `storedMessage` is reset back to null.
     * @return a String object representing the last-printed String by the Ui object to `System.out`
     */
    public String popStoredMessage() {
        assert this.storedMessage != null;
        String s = this.storedMessage;
        this.storedMessage = null;
        return s;
    }

    public String nextLine() {
        return sc.nextLine();
    }

    public void greetUser() {
        printMessage("Hello I'm " + chatbotName + "\n\nPlease call for `help` if you need anything!");
    }

    public void promptUser() {
        printMessage("Input your command below:");
    }

    public void farewellUser() {
        printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * This method prints out `text` in a formatted way, enveloping it with horizontal lines for aesthetic purposes. It
     * prints the result out to `System.out`.
     * @param text A String object representing the formatted reply by the chatbot
     */
    public void printMessage(String text) {
        // prints text with horizontal lines above and below it
        System.out.println(HORIZONTAL_LINE);
        System.out.println(text);
        System.out.println(HORIZONTAL_LINE);
        System.out.println();

        this.setStoredMessage(text);
    }

    /**
     * This method takes in a TaskList object as input and iterates through all the tasks in it, printing each one of
     * them out sequentially.
     * @param tasks A TaskList object that represents the undeleted tasks stored by the chatbot
     */
    public void printAllTasks(TaskList tasks) {
        StringBuilder allTasksString = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getAllTasks().size(); i++) {
            Task newTask = tasks.getAllTasks().get(i);
            allTasksString.append((i + 1)).append(".").append(newTask.toString()).append("\n");
        }
        printMessage(allTasksString.toString());
    }

    /**
     * This method is run only when the user inputs a "help" command, in which case, the instructions of how to use the
     * chatbot is printed onto `System.out`. It takes in a String fileSavePath to print out the directory on the user's
     * machine where their data will be stored.
     * @param fileSavePath A String representing the (relative) file path to save the user's data
     */
    public void printInputSpecifications(String fileSavePath) {
        String message = "Please enter your commands:\n";
        message += "Adding Tasks:\n";
        message += "1. Todo tasks: todo <description>\n";
        message += "2. Deadline tasks: deadline <description> /by <date>\n";
        message += "3. Event tasks: event <description> /from <date> /to <date>\n\n";

        message += "Marking and Unmarking Tasks:\n";
        message += "1. Marking tasks as completed: mark <integer>\n";
        message += "2. Unmarking tasks are uncompleted: unmark <integer>\n\n";

        message += "Deleting Tasks:\n";
        message += "1. delete <integer>\n\n";

        message += "Others:\n";
        message += "1. Quit: bye\n";
        message += "2. Manual: help\n\n";

        message += "FYI: Your data is saved locally in: " + fileSavePath;
        printMessage(message);
    }

    public void printInvalidCommandTypeExceptionResponse() {
        printMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * The chatbot's reply upon successful deletion of a task is printed out to `System.out`.
     * @param taskToDelete A Task object representing the task the user intended to delete
     * @param numTotalTasks An int variable that represents the number of remaining tasks after deleting the task
     */
    public void printSuccessfulDeleteResponse(Task taskToDelete, int numTotalTasks) {
        printMessage("Noted. I've removed this task:\n  " + taskToDelete.toString() + "\nNow you have "
                + numTotalTasks + " task(s) in the list.");
    }

    public void printSuccessfulMarkResponse(Task taskToMark) {
        printMessage("Nice! I've marked this task as done:\n  " + taskToMark);
    }

    public void printSuccessfulUnmarkResponse(Task taskToUnmark) {
        printMessage("OK, I've marked this task as not done yet:\n" + taskToUnmark);
    }

    /**
     * The chatbot's reply upon successful addition of a task is printed out to `System.out`.
     * @param newTask A Task object representing the task the user intended to add
     * @param numTotalTasks An int variable that represents the number of remaining tasks after adding the task
     */
    public void printSuccessfulAddTaskResponse(Task newTask, int numTotalTasks) {
        printMessage("Got it. I've added this task:\n  " + newTask + "\nNow you have " + numTotalTasks
                + " task(s) in the list.");
    }

    public void printInvalidMarkOrUnmarkResponse(int numTotalTasks) {
        printMessage("☹ OOPS!!! The description of a mark/unmark must be between 1 and " + numTotalTasks + ".");
    }

    public void printEmptyByExceptionResponse() {
        printMessage("By clause cannot be empty!");
    }

    public void printEmptyToExceptionResponse() {
        printMessage("To clause cannot be empty!");
    }

    public void printEmptyFromExceptionResponse() {
        printMessage("From clause cannot be empty!");
    }

    public void printInvalidByExceptionResponse() {
        printMessage("By clause must be in the following format: YYYY-MM-DD");
    }

    public void printInvalidToExceptionResponse() {
        printMessage("To clause must be in the following format: YYYY-MM-DD");
    }

    public void printInvalidFromExceptionResponse() {
        printMessage("From clause must be in the following format: YYYY-MM-DD");
    }

    public void printEmptyTodoDescriptionResponse() {
        printMessage("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    public void printInvalidDeleteResponse(int numTotalTasks) {
        printMessage("☹ OOPS!!! The description of a delete must be between 1 and " + numTotalTasks + ".");
    }

    public void printNotAnIntegerExceptionResponse() {
        printMessage("Please enter a valid integer (E.g. mark 1, unmark 8, delete 3)");
    }

    public void printFileNotFoundResponse(String filePath) {
        printMessage("No previous datafile found in " + filePath + ". Creating new task list for you!");
    }

    public void printTaskNotFoundExceptionResponse() {
        printMessage("☹ OOPS!!! No such task exists in your task list");
    }

    /**
     * Prints message for when the file exists in the user's data directory. However, no data is found there.
     */
    public void printNoDataResponse() {
        printMessage("Datafile located. However, it is empty. Creating a new task list for you!");
    }

    public void printSaveFailResponse(String savePath) {
        System.out.println("Failed to write to " + savePath);
    }

    /**
     * This method is run only when the user inputs a "find" command, in which case, this method prints the tasks that
     * match the user's input query.
     * @param matchingTasks An ArrayList object that stores the matching tasks to be printed out
     */
    public void printMatchingTasks(ArrayList<Task> matchingTasks) {
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            Task newTask = matchingTasks.get(i);
            message.append((i + 1)).append(".").append(newTask.toString()).append("\n");
        }
        printMessage(message.toString());
    }
}
