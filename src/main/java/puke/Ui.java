package puke;

import java.util.Scanner;

import puke.task.Task;

/**
 * A class that handles all messages printed in the UI
 */
public class Ui {
    private static final String SEPARATOR = "____________________________________________________________";

    private final Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the first token from a line of input.
     *
     * @return Command string.
     */
    public String command() {
        return sc.next();
    }

    /**
     * Returns the remainder of the line of input after the command string has been removed.
     *
     * @return Remainder of line.
     */
    public String input() {
        return sc.nextLine();
    }

    /**
     * Prints the separator line.
     */
    public void line() {
        System.out.println(Ui.SEPARATOR);
    }

    /**
     * Prints the welcome logo and message.
     */
    void startup() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| |_| | | | | |/ / _ \\\n"
                + "| ___/| |_| |    | __/\n"
                + "| |    \\__,_|_|\\_\\___|\n"
                + "|_|";
        System.out.println("Salutations! I hereby would like to inform you that my identity is that of\n"
                + logo
                + "\nAn exceedingly verbose conversation simulation program.");
        System.out.println(SEPARATOR);
    }

    /**
     * Returns the separator line used after each command.
     * @return the separator line as a string.
     */
    public static String separator() {
        return SEPARATOR;
    }

    /**
     * Returns the default error message.
     * @return the error message as a string.
     */
    public static String errorMessage() {
        return "Unfortunately, the circumstances preceding this has necessitated that I issue"
                + " an apology for the input that I have received is unrecognised.";
    }
    /**
     * Returns the exit message.
     *
     * @return The exit message.
     */
    public String exit() {
        return "It appears that the user has decided to close the program as indicated by the command of "
                + "which this is the function being issued and therefore,\n"
                + "I shall bid thee farewell and wish thee great fortune in your future endeavors.";
    }

    /**
     * Returns the message for listing tasks in the list.
     *
     * @return The message for listing tasks in the list.
     */
    public String list() {
        return "Here is the collection of items, previously designated to be known as Tasks, "
                + "that you have inputted over a previous unspecified period of time\n"
                + "that may or may not require urgent attention, but will nevertheless necessitate "
                + "some level of action within an either\n"
                + "indicated or non indicated time period.";
    }

    /**
     * Returns the message for marking a task as done.
     *
     * @param index Index of the task that has been marked.
     * @return The message indicating that the task has been done.
     */
    public String mark(int index) {
        return "I have been made aware of your desire to indicate that the task numbered "
                + index
                + " has been since been achieved as of the time at which you hve stipulated as so.";
    }

    /**
     * Returns the message for marking a task as undone.
     *
     * @return The message indicating that that task has been unmarked.
     */
    public String unmark() {
        return "Very well. I have acknowledged your request to unmark the task of "
                + "specified index as having been completed and\n"
                + "will now proceed to set said task of specified index to be considered as "
                + "having not yet been completed.";
    }

    /**
     * Returns the message indicating that a new to do task has been added to the list.
     *
     * @param tl The task list.
     * @return The message.
     * @throws PukeException If the task or values used in the list are out of bounds.
     */
    public String toDo(TaskList tl) throws PukeException {
        return "Understood. I have hereby created a task known to require doing at a future time "
                + "but with no such time being specified and inserted it into "
                + "the overall collection of said tasks that require action.\n"
                + "Here is a display of the added deadline task: "
                + tl.get(tl.size() - 1)
                + "\n"
                + "You now, in total, have "
                + tl.size()
                + " of these tasks recorded within said collection.";
    }

    /**
     * Returns the message indicating that a new Deadline task has been added to the list.
     *
     * @param tl The task list.
     * @return The message.
     * @throws PukeException If the task or values used in the list are out of bounds.
     */
    public String deadline(TaskList tl) throws PukeException {
        return "Understood. I have hereby created a task known to require doing at a future time "
                + "alongside the stipulated time that you have indicated and inserted it into "
                + "the overall collection of these tasks that require action.\n"
                + "Here is a display of the added deadline task: "
                + tl.get(tl.size() - 1)
                + "\n"
                + "You now, in total, have "
                + tl.size()
                + " of these tasks recorded within said collection.";
    }

    /**
     * Returns the message indicating that a new Event task has been added to the list.
     *
     * @param tl The task list.
     * @return The message.
     * @throws PukeException If the task or values used in the list are out of bounds.
     */
    public String event(TaskList tl) throws PukeException {
        return "Understood. I have hereby created a task known to require participation for a set period of time "
                + "alongside this stipulated duration that you have indicated and inserted it into "
                + "the overall collection of these tasks that require action.\n"
                + "Here is a display of the added deadline task: "
                + tl.get(tl.size() - 1)
                + "\n"
                + "You now, in total, have "
                + tl.size()
                + " of these tasks recorded within said collection.";
    }

    /**
     * Returns the message indicating that a Task has been removed from the list
     *
     * @param hold The task that was removed.
     * @param tl The task list.
     * @return The message.
     */
    public String delete(Task hold, TaskList tl) {
        return "I have acknowledged your request to have the task allocated to the specific index at which "
                + "you have mentioned removed from the collection of all\n"
                + "such tasks, colloquially known as your To Do list.\n"
                + "The task in question that has been deleted is: "
                + hold
                + "\n"
                + "As of this current moment, there are a total of "
                + tl.size()
                + " occurrences of tasks in your list.";
    }

    /**
     * Returns the message indicating that all tasks have been cleared from the list.
     *
     * @return The message.
     */
    public String clear() {
        return "Well I certainly hope you had meant to do that because I am not going too ask for your confirmation. "
                + "As per the aforementioned instructions, I shall now"
                + "purge all of the tasks that you have previously recorded and designated as requiring attention.";
    }

    /**
     * Returns the message indicating that the find command has been executed.
     * @return The message.
     */
    public String find() {
        return "As per the instructions provided, I shall initiate a search into your list of items, of which "
                + "we have previously declared to be known as tasks due too their relatively \n"
                + "urgent need of attention within a specified or unspecified frame of time, for those of which have "
                + "an alphabetical similarity to the frame of reference that you have provided.";
    }
}
