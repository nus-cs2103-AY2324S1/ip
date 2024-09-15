package duke;

/**
 * The duke.Parser class processes commands by breaking it up to words.
 */
public class Parser {
    /**
     * Splits the input into array of [command word, details].
     * @return The array of parsed input.
     */
    public String[] commandSplit(String command) {
        return command.split(" ", 2);
    }
    /**
     * Splits the input into array of [deadline details, deadline due date].
     * @return The array of parsed input.
     */
    public String[] deadlineDetails(String task) {
        return task.split("/by ", 2);
    }
    /**
     * Splits the input into array of [event details, event from, event to].
     * @return The array of parsed input.
     */
    public String[] eventDetails(String task) {
        return task.split(" /", 3);
    }
    /**
     * Splits the input containing details regarding a task.
     * @return The array of parsed input.
     */
    public String[] storageSplit(String task) {
        return task.split("~", 6);
    }
    /**
     * Splits the input containing details about start and end time of an event task.
     * @return The array of parsed input.
     */
    public String[] storageTimeSplit(String time) {
        return time.split(" - ", 2);
    }
}
