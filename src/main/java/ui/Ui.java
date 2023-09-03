package ui;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Task;
import tasks.TaskList;

/**
 * Represents the user interface.
 */
public class Ui {

    /** Scanner to handle user input. */
    private Scanner scanner;
    /** UI stored responses */
    private ArrayList<String> responses = new ArrayList<>();
    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    /**
     * Shows the error message when loading file.
     */
    public void showLoadingError() {
        responses.add("Error loading file");
    }
    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        String botName = "Dude";
        responses.add("Hello! I'm " + botName);
        responses.add("What can I do for you?");
    }

    /**
     * Shows a line.
     */
    public void showLine() {
        responses.add("____________________________________________________________");
    }

    /**
     * Reads the command from the user.
     * @return String fullCommand
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows the error message.
     */
    public void showError(String errorMessage) {
        responses.add(errorMessage);
    }

    /**
     * Shows the task added message.
     */
    public void showTaskAdded(Task task, int taskCount) {
        responses.add("Got it. I've added this task:");
        responses.add(task.toString());
        responses.add("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Shows the task deleted message.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        responses.add("Noted. I've removed this task:");
        responses.add(task.toString());
        responses.add("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Shows the schedule on specific date.
     */
    public void showSchedule(String output) {
        if (output.equals("")) {
            responses.add("There are no tasks on this date.");
        } else {
            responses.add("Here are the tasks on this date:");
            responses.add(output);
        }
    }

    /**
     * Shows the list of tasks.
     */
    public void showList(TaskList userTasks) {
        if (userTasks.size() == 0) {
            responses.add("You have no tasks.");
        } else {
            responses.add("Here are the tasks in your list:");
            responses.add(userTasks.toString());
        }
    }

    /**
     * Shows the goodbye message.
     */
    public void showGoodbye() {
        responses.add("Bye. Hope to see you again soon!");
    }

    /**
     * Shows the invalid command message.
     */
    public void showInvalidCommandMessage() {
        responses.add("Command is invalid. Please try again.");
    }

    /**
     * Shows the task marked message.
     */
    public void showTaskMarked(Task task) {
        responses.add("Nice! I've marked this task as done:");
        responses.add(task.toString());
    }

    /**
     * Shows the task unmarked message.
     */
    public void showTaskUnmarked(Task task) {
        responses.add("Nice! I've marked this task as undone:");
        responses.add(task.toString());
    }

    /**
     * Shows the tasklist saved message.
     */
    public void showSave() {
        responses.add("Tasks saved to file.");
    }

    /**
     * Shows the tasklist loaded message.
     */
    public void showLoad() {
        responses.add("Tasks loaded from file.");
    }

    /**
     * Shows the tasklist.
     * @param output The list of matching tasks.
     */
    public void showFind(String output) {
        responses.add("Here are the matching tasks in your list:");
        responses.add(output);
    }

    public String getOutput(String... preStrings) {
        //convert output to string
        String output = "";
        for (String preString : preStrings) {
            output += preString + "\n";
        }
        for (String response : responses) {
            output += response + "\n";
        }
        responses.clear();
        return output;
    }
}
