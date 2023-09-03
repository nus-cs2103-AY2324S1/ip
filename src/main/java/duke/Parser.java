package duke;

/**
 * The Parser class creates Parser objects that is used by the Main class
 * to simplify the parsing and processing of input, including the
 * throwing of exceptions.
 */
public class Parser {

    String[] input;
    String fullLine;

    /**
     * Creates a Parser object by taking in a single string input and parsing information.
     *
     * @param input The String input to be parsed.
     */
    Parser(String input) {
        this.fullLine = input;
        this.input = input.split(" ");
    }

    /** Returns the first value of the parsed input */
    public String command() {
        return this.input[0];
    }

    /** Returns the second value of the parsed input as an integer. */
    public int number() {
        return Integer.parseInt(this.input[1]);
    }

    /**
     * Returns the second value of the parsed input as an offset integer.
     * Useful for dealing with variances between user input and the 0-index
     * of ArrayLists.
     *
     * @return The second value of parsed input with unit subtraction.
     * */
    public int num() {
        return this.number() - 1;
    }

    /**
     * Returns a ToDo object with all relevant information.
     *
     * @return The ToDo object.
     * @throws DukeException If incorrect input.
     */
    public ToDo parseTodo() throws DukeException {
        if (fullLine.split("todo ").length < 1) {
            new Ui().todoErrorPrinter();
            throw new DukeException(new Ui().todoErrorString());
        }

        String taskName = fullLine.split("todo ")[1];
        return new ToDo(taskName);
    }

    /**
     * Returns a Deadline object with all relevant information.
     *
     * @return The Deadline object.
     * @throws DukeException If incorrect input.
     */
    public Deadline parseDeadline() throws DukeException {
        if (fullLine.split("/by ").length < 1) {
            new Ui().deadlineErrorPrinter();
            throw new DukeException(new Ui().deadlineErrorString());
        }
        String longName = fullLine.split("/by ")[0];
        String date = fullLine.split("/by ")[1];
        String taskName = longName.split("deadline ")[1];
        String deadlineName = taskName +
                String.format("(by: %s)", date);
        return new Deadline(deadlineName);
    }

    /**
     * Returns a Event object with all relevant information.
     *
     * @return The Event object.
     * @throws DukeException If incorrect input.
     */
    public Event parseEvent() throws DukeException {
        if (fullLine.split("/").length < 3) {
            new Ui().eventErrorPrinter();
            throw new DukeException(new Ui().eventErrorString());
        }
        String[] longNameArray = fullLine.split("/");
        String longName = longNameArray[0];
        String fromTime = longNameArray[1];
        String endTime = longNameArray[2];
        String taskName = longName.split("event ")[1];
        String eventName = taskName +
                String.format("(from: %s to: %s)",
                        fromTime, endTime);
        return new Event(eventName);
    }

}
