package duke.ui.text;

import java.time.LocalDate;
import java.util.Scanner;

import duke.parse.DateTimeManager;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Deals with standard input and output.
 * Prints out the output and takes in input as requested.
 */
public class TextUi implements Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private final Scanner scanner = new Scanner(System.in);
    private final String errorPrepend;
    private final String errorAppend;

    /**
     * Instantiates a UI, with a string to prepend and a string to append every error message.
     * @param errorPrepend the string to prepend every error message
     * @param errorAppend the string to append every error message
     */
    public TextUi(String errorPrepend, String errorAppend) {
        this.errorPrepend = errorPrepend;
        this.errorAppend = errorAppend;
    }

    /**
     * Invoked at the start of the interaction, to greet the user,
     * after data has been loaded / error has been handled.
     * @param name the name of the bot
     */
    @Override
    public void initialise(String name, String[] args) {
        System.out.println(TextUi.HORIZONTAL_LINE);
        System.out.print("Hello from ");
        System.out.println(name);
        System.out.println("What can I do for you?");
    }

    /**
     * Invoked when data is being loaded.
     */
    @Override
    public void notifyDataLoading() {
        System.out.println(TextUi.HORIZONTAL_LINE);
        System.out.println("Loading data from hard disk ...");
    }

    /**
     * Invoked when data has been loaded.
     */
    @Override
    public void notifyDataLoaded() {
        System.out.println("Done loading.");
    }

    /**
     * Invoked when there is an IO error upon loading
     */
    @Override
    public void notifyLoadingIoError() {
        System.out.println("Quack, an error has occurred while trying to save data to hard disk.");
        System.out.println("Starting with an empty task list.");
    }

    /**
     * Notifies that file is corrupted and cannot be loaded,
     * and ask the user on the course of action to take.
     * @return whether the user has decided to exit the program.
     */
    @Override
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
    @Override
    public String takeInput(String prompt) {
        System.out.println(TextUi.HORIZONTAL_LINE);
        System.out.print(prompt);
        String input = this.scanner.nextLine();
        System.out.println(TextUi.HORIZONTAL_LINE);
        return input;
    }

    /**
     * Invoked at the end of the programme, to leave an exit message to user.
     */
    @Override
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(TextUi.HORIZONTAL_LINE);
        this.scanner.close();
    }

    /**
     * Notifies user of a user-input error.
     */
    @Override
    public void notifyError(String message) {
        System.out.println(this.errorPrepend + message + this.errorAppend);
    }

    /**
     * Notifies user that a task has been marked done
     * @param task the task to notify
     */
    @Override
    public void notifyMarkDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Notifies user that a task has been marked not done
     * @param task the task to notify
     */
    @Override
    public void notifyMarkNotDone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Notifies user that a task has been removed
     * @param task the task removed
     */
    @Override
    public void notifyRemoved(Task task) {
        System.out.println("Noted, I've removed this task:");
        System.out.println(task);
    }

    /**
     * Notifies that a list of task is going to be displayed.
     * Does not display the tasks itself.
     * @param type type of task (todo/deadline/event/default)
     * @param isExcludingDone whether to exclude tasks already done
     * @param date the date before which to display deadlines before or events happening on,
     *             null if not to filter by date
     */
    @Override
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
    @Override
    public void notifyAdded(Task task) {
        System.out.println("Got it, I've added this task to the list:");
        System.out.println(task);
    }

    /**
     * Notifies the user that data is in the process of being saved to disk.
     */
    @Override
    public void notifyDataSaving() {
        System.out.println("Saving data ...");
    }

    /**
     * Notifies the user that data has been saved to disk.
     */
    @Override
    public void notifyDataSaved() {
        System.out.println("Done saving");
    }

    /**
     * Show task count.
     * @param count the number of task in the list
     */
    @Override
    public void showTaskCount(int count) {
        System.out.println("Now you have " + count + " in the list.");
    }

    /**
     * Notify the user of the search result.
     * @param input the search parameter
     */
    @Override
    public void notifyFind(String input) {
        System.out.println("Here are the tasks that match \"" + input + "\"");
    }

    /**
     * Display custom data
     */
    @Override
    public void displayData(String data) {
        System.out.println(data);
    }
}
