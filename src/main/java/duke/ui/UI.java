package duke.ui;

import duke.parse.DateTimeManager;
import duke.task.Task;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Deals with standard input and output.
 * Prints out the output and takes in input as requested.
 */
public class UI {
    private static String HORIZONTAL_LINE = "____________________________________________________________";

    private final Scanner SCANNER = new Scanner(System.in);
    private final String ERROR_PREPEND;
    private final String ERROR_APPEND;

    /**
     * Instantiates a UI, with a string to prepend and a string to append every error message.
     * @param errorPrepend the string to prepend every error message
     * @param errorAppend the string to append every error message
     */
    public UI(String errorPrepend, String errorAppend) {
        this.ERROR_PREPEND = errorPrepend;
        this.ERROR_APPEND = errorAppend;
    }

    /**
     * Invoked at the start of the interaction, to greet the user,
     * after data has been loaded / error has been handled.
     * @param name the name of the bot
     */
    public void start(String name) {
        System.out.println(UI.HORIZONTAL_LINE);
        System.out.print("Hello from ");
        System.out.println(name);
        System.out.println("What can I do for you?");
    }

    /**
     * Invoked when data is being loaded.
     */
    public void notifyDataLoading() {
        System.out.println(UI.HORIZONTAL_LINE);
        System.out.println("Loading data from hard disk ...");
    }

    /**
     * Invoked when data has been loaded.
     */
    public void notifyDataLoaded() {
        System.out.println("Done loading.");
    }

    /**
     * Invoked when there is an IO error upon loading
     */
    public void notifyLoadingIOError() {
        System.out.println("Quack, an error has occurred while trying to save data to hard disk.");
        System.out.println("Starting with an empty task list.");
    }

    /**
     * Notifies that file is corrupted and cannot be loaded,
     * and ask the user on the course of action to take.
     * @return whether the user has decided to exit the program.
     */
    public boolean handleFileCorrupted() {
        System.out.println("Quack, memory was found to be corrupted!");
        System.out.println("What do you wish to do?");
        System.out.println("1. Quit, let me restore the data manually");
        System.out.println("2. Continue with an empty task list");

        while (true) {
            String response = this.takeInput("Please indicate your option (1/2): ");
            switch (response) {
            case "1":
                this.exit();
                return false;
            case "2":
                return true;
            default:
                System.out.print("Quack, I do not understand your option, please indicate again (1/2): ");
            }
        }
    }

    /**
     * Takes input from the user
     * @return the input from the user
     */
    public String takeInput(String prompt) {
        System.out.println(UI.HORIZONTAL_LINE);
        System.out.print(prompt);
        String input = this.SCANNER.nextLine();
        System.out.println(UI.HORIZONTAL_LINE);
        return input;
    }

    /**
     * Invoked at the end of the programme, to leave an exit message to user.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(UI.HORIZONTAL_LINE);
        this.SCANNER.close();
    }

    /**
     * Notifies user of a user-input error.
     */
    public void notifyError(String message) {
        System.out.println(this.ERROR_PREPEND + message + this.ERROR_APPEND);
    }

    /**
     * Notifies user that a task has been marked done
     * @param task the task to notify
     */
    public void notifyMarkDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Notifies user that a task has been marked not done
     * @param task the task to notify
     */
    public void notifyMarkNotDone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Notifies user that a task has been removed
     * @param task the task removed
     */
    public void notifyRemoved(Task task) {
        System.out.println("Noted, I've removed this task:");
        System.out.println(task);
    }

    public enum Type {
        TODO,
        DEADLINE,
        EVENT,
        DEFAULT
    }

    /**
     * Notifies that a list of task is going to be displayed.
     * Does not display the tasks itself.
     * @param type type of task (todo/deadline/event)
     * @param isExcludingDone whether to exclude tasks already done
     * @param date the date before which to display deadlines before or events happening on,
     *             null if not to filter by date
     */
    public void notifyList(Type type, boolean isExcludingDone, LocalDate date) {
        String typeString;
        switch (type) {
        case TODO:
            typeString = "to-do tasks";
            break;
        case DEADLINE:
            typeString = "deadlines";
            break;
        case EVENT:
            typeString = "events";
            break;
        default:
            typeString = "tasks";
            break;
        }
        System.out.println(
                "Alright, here is your list of "
                + typeString
                + (isExcludingDone ? " not done" : "")
                + (date != null
                        ? (type == Type.DEADLINE
                            ? " before "
                            : type == Type.EVENT
                            ? " happening on "
                            : " for "
                        ) + DateTimeManager.dateToDisplay(date)
                        : ""
                ) + ":"
        );
    }

    /**
     * Notifies the user that a task has been added.
     * @param task the task added
     */
    public void notifyAdded(Task task) {
        System.out.println("Got it, I've added this task to the list:");
        System.out.println(task);
    }

    /**
     * Echoes an input back to the user.
     * @param input the input from the user
     */
    public void echo(String input) {
        if (input.equals("quack")) {
            System.out.println("Quack quack quack");
        } else {
            System.out.println("Quack, what do you mean when you say " + input);
        }
    }

    /**
     * Notifies the user that data is in the process of being saved to disk.
     */
    public void notifyDataSaving() {
        System.out.println("Saving data ...");
    }

    /**
     * Notifies the user that data has been saved to disk.
     */
    public void notifyDataSaved() {
        System.out.println("Done saving");
    }

    public void showTaskCount(int count) {
        System.out.println("Now you have " + count + " in the list.");
    }
}
