package duke;

/**
 * This class parses and runs commands for various inputs.
 */
public class Parser {

    String[] input;
    String fullLine;

    Parser(String input) {
        this.fullLine = input;
        this.input = input.split(" ");
    }

    public String command() {
        return this.input[0];
    }
    public int number() {
        return Integer.parseInt(this.input[1]);
    }

    public int num() {
        return this.number() - 1;
    }

    public String word() {
        return this.input[1];
    }

    public ToDo parseTodo() throws DukeException {
        if (fullLine.split("todo ").length < 1) {
            new Ui().todoErrorPrinter();
            throw new DukeException(new Ui().todoErrorString());
        }

        String taskName = fullLine.split("todo ")[1];
        return new ToDo(taskName);
    }

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
