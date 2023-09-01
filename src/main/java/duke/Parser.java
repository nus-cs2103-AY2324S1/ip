package duke;

/**
 * The duke.Parser class processes commands by breaking it up to words.
 */
public class Parser {
    public String[] commandSplit(String command) {
        return command.split(" ", 2);
    }

    public String[] deadlineDetails(String task) {
        return task.split("/by ", 2);
    }
    public String[] eventDetails(String task) {
        return task.split(" /", 3);
    }
    public String[] storageSplit(String task) {
        return task.split("~", 5);
    }
    public String[] storageTimeSplit(String time) {
        return time.split(" - ", 2);
    }
}
