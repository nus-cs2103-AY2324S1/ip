package taskmate.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import taskmate.exceptions.FileCorruptedException;
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

    /**
     * @return the next line of user input
     */
    public String nextLine() {
        return sc.nextLine();
    }

    /**
     * Prints greet message
     */
    public void greetUser() {
        printMessage("Hello I'm " + chatbotName + "\n\nPlease call for `help` if you need anything!");
    }

    /**
     * Prints message to prompt the user
     */
    public void promptUser() {
        printMessage("Input your command below:");
    }

    /**
     * Prints message when user exits the application
     */
    public void farewellUser() {
        printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out `text` in a formatted way, enveloping it with horizontal lines for aesthetic purposes
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
     * Takes in a TaskList object as input and iterates through all the tasks in it, printing each one of
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
     * Takes in a String fileSavePath to print out the directory on the user's machine where their data will be stored.
     * This method is run only when the user inputs a "help" command, in which case, the instructions of how to use the
     * chatbot is printed.
     * @param fileSavePath A String representing the (relative) file path to save the user's data
     */
    public void printInputSpecifications(String fileSavePath) {
        String message = "Please enter your commands:\n";
        message += HORIZONTAL_LINE + "\n";

        message += "Adding Tasks:\n";
        message += "1. Todo tasks: todo <name>\n";
        message += "2. Deadline tasks: deadline <name> /by <date>\n";
        message += "3. Event tasks: event <name> /from <date> /to <date>\n";
        message += HORIZONTAL_LINE + "\n";

        message += "Marking and Unmarking Tasks:\n";
        message += "1. Marking tasks as completed: mark <integer>\n";
        message += "2. Unmarking tasks as incompleted: unmark <integer>\n";
        message += HORIZONTAL_LINE + "\n";

        message += "Deleting Tasks:\n";
        message += "1. delete <integer>\n";
        message += HORIZONTAL_LINE + "\n";

        message += "Updating Tasks:\n";
        message += "1. update <integer> <TAG> <newValue> ...\n\n";
        message += "TAG\n";
        message += "Todo Task: /name newName\n";
        message += "Deadline Task: /name newName AND/OR /by newBy\n";
        message += "Event Task: /name newName AND/OR /from newFrom AND/OR /to newTo\n\n";
        message += "newValue\n";
        message += "/name: name of task\n";
        message += "/by, /from, /to: must be of the form 'YYYY-mm-dd'\n";
        message += "...: More '<TAG> <newValue>' pairs\n";
        message += HORIZONTAL_LINE + "\n";

        message += "Listing Tasks:\n";
        message += "1. Listing down all your tasks: list\n";
        message += "2. Listing down tasks that match a specific keyword / keyphrase: find <query>\n";
        message += HORIZONTAL_LINE + "\n";

        message += "Others:\n";
        message += "1. Quit and save data: bye\n";
        message += "2. Manual: help\n";
        message += HORIZONTAL_LINE + "\n";

        message += "FYI: Your data is saved locally in: " + fileSavePath + "\n";
        message += HORIZONTAL_LINE + "\n";
        printMessage(message);
    }

    /**
     * Prints a message when a prompt that is unable to be parsed is made
     */
    public void printInvalidCommandTypeExceptionResponse() {
        printMessage("OOPS!!!:( I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints a reply upon successful deletion of a task.
     * @param taskToDelete A Task object representing the task the user intended to delete
     * @param numTotalTasks An int variable that represents the number of remaining tasks after deleting the task
     */
    public void printSuccessfulDeleteResponse(Task taskToDelete, int numTotalTasks) {
        printMessage("Noted. I've removed this task:\n  " + taskToDelete.toString() + "\nNow you have "
                + numTotalTasks + " task(s) in the list.");
    }

    /**
     * Prints a reply upon successful marking of a task.
     * @param taskToMark An int variable that represents the task marked as completed
     */
    public void printSuccessfulMarkResponse(Task taskToMark) {
        printMessage("Nice! I've marked this task as done:\n  " + taskToMark);
    }

    /**
     * Prints a reply upon successful unmarking of a task.
     * @param taskToUnmark An int variable that represents the task unmarked to incomplete
     */
    public void printSuccessfulUnmarkResponse(Task taskToUnmark) {
        printMessage("OK, I've marked this task as not done yet:\n" + taskToUnmark);
    }

    /**
     * Prints a reply reply upon successful addition of a task.
     * @param newTask A Task object representing the task the user intended to add
     * @param numTotalTasks An int variable that represents the number of remaining tasks after adding the task
     */
    public void printSuccessfulAddTaskResponse(Task newTask, int numTotalTasks) {
        printMessage("Got it. I've added this task:\n  " + newTask + "\nNow you have " + numTotalTasks
                + " task(s) in the list.");
    }

    /**
     * Prints a message when the user leaves the "by" clause of a Deadline task empty
     */
    public void printEmptyByExceptionResponse() {
        printMessage("By clause cannot be empty!");
    }

    /**
     * Prints a message when the user leaves the "to" clause of an Event task empty
     */
    public void printEmptyToExceptionResponse() {
        printMessage("To clause cannot be empty!");
    }

    /**
     * Prints a message when the user leaves the "from" clause of an Event task empty
     */
    public void printEmptyFromExceptionResponse() {
        printMessage("From clause cannot be empty!");
    }

    /**
     * Prints a message when the user writes the "by" clause in an incorrect format
     */
    public void printInvalidByExceptionResponse() {
        printMessage("By clause must be in the following format: YYYY-MM-DD");
    }

    /**
     * Prints a message when the user writes the "to" clause in an incorrect format
     */
    public void printInvalidToExceptionResponse() {
        printMessage("To clause must be in the following format: YYYY-MM-DD");
    }

    /**
     * Prints a message when the user writes the "from" clause in an incorrect format
     */
    public void printInvalidFromExceptionResponse() {
        printMessage("From clause must be in the following format: YYYY-MM-DD");
    }

    /**
     * Prints a message when the user inputs a non-integer when an integer is expected
     */
    public void printNotAnIntegerExceptionResponse() {
        printMessage("Please enter a valid integer (E.g. mark 1, unmark 8, delete 3, update 2 <TAG> <newValue>...)");
    }

    /**
     * Prints a message when a previous file containing past tasks data cannot be found
     * @param filePath a String object representing the expected filepath of the tasks data
     */
    public void printFileNotFoundResponse(String filePath) {
        printMessage("No previous datafile found in " + filePath + ". Creating new task list for you!");
    }

    /**
     * Prints a message when the user attempts to reference a task that does not exist in their task-list
     */
    public void printTaskNotFoundExceptionResponse() {
        printMessage("OOPS!!!:( No such task exists in your task list");
    }

    /**
     * Prints message for when the file exists in the user's data directory. However, no data is found there.
     */
    public void printNoDataResponse() {
        printMessage("Datafile located. However, it is empty. Creating a new task list for you!");
    }

    /**
     * Prints a fail message if an exception occurs when attempting to save the user's tasks data
     * @param savePath a String object representing the intended filepath of the user's tasks data
     */
    public void printSaveFailResponse(String savePath) {
        System.out.println("Failed to write to " + savePath);
    }

    /**
     * Prints the tasks that match the user's input query when executing a `find` command
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

    public void printFileCorruptedResponse(FileCorruptedException e) {
        printMessage(e.getMessage());
    }

    /**
     * Prints a message upon successful updating of a task
     * @param updateIndex An int index in the task list that is updated
     * @param successfulChanges An ArrayList of successful updates
     */
    public void printSuccessfulUpdateResponse(int updateIndex, HashMap<String, String> successfulChanges) {
        StringBuilder message = new StringBuilder("Updates successfully made to task " + updateIndex + ":\n");
        for (HashMap.Entry<String, String> attributeValuePair : successfulChanges.entrySet()) {
            String attribute = attributeValuePair.getKey();
            String newValue = attributeValuePair.getValue();
            message.append(attribute)
                    .append(": ")
                    .append(newValue)
                    .append("\n");
        }
        printMessage(message.toString());
    }

    /**
     * Prints a message when unsuccessful update to Todo task is made
     */
    public void printInvalidTodoUpdateException() {
        String message = "Invalid update to Todo task!\n";
        message += "Syntax (for Todo tasks): update <integer> /name <newName>";
        printMessage(message);
    }

    /**
     * Prints a message when unsuccessful update to Deadline task is made
     */
    public void printInvalidDeadlineUpdateException() {
        String message = "Invalid update to Deadline task!\n";
        message += "Syntax (for Deadline tasks): update <integer> /name <newName> /by <YYYY-mm-dd>";
        printMessage(message);
    }

    /**
     * Prints a message when unsuccessful update to Event task is made
     */
    public void printInvalidEventUpdateException() {
        String message = "Invalid update to Event task!\n";
        message += "Syntax (for Event tasks): update <integer> /name <newName> /from <YYYY-mm-dd> /to <YYYY-mm-dd>";
        printMessage(message);
    }

    /**
     * Prints a message when the user makes an update command without any clauses
     * Possible clauses: /name, /by, /from, /to
     */
    public void printClauselessUpdateExceptionResponse() {
        String message = "Invalid update command format!\n";
        message += "Syntax for update command: update <integer> <TAG> <newValue> ...";
        printMessage(message);
    }
}
