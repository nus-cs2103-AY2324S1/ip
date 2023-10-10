package duke;

import javafx.scene.web.HTMLEditorSkin;

import java.io.IOException;

/**
 * The Parser class creates Parser objects that is used
 * to simplify the parsing and processing of input, including the
 * throwing and/or handling of exceptions.
 */
public class Parser {

    private String[] input;
    private String fullLine;

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
        assert (this.input[0] instanceof String);
        return this.input[0];
    }

    /** Returns the second value of the parsed input as an integer. */
    public int number() {
        return Integer.parseInt(this.input[1]);
    }

    /** Returns true if the input string only contains a singular command */
    public boolean commandOnly() {
        return this.input.length == 1 ? true : false;
    }

    /**
     * Returns the second value of the parsed input as an offset integer.
     * Useful for dealing with variances between user input and the 0-index
     * of Java ArrayLists.
     *
     * @return The second item of parsed input with unit subtraction.
     * */
    public int num() {
        assert (this.number() >= 0);
        return this.number() - 1;
    }

    /** Returns the second item of the parsed input as a String. */
    public String word() {
        assert (this.input[1] instanceof String);
        return this.input[1];
    }

    /**
     * Returns a ToDo object with all relevant information.
     *
     * @return The ToDo object.
     * @throws DukeException If incorrect input.
     */
    public ToDo parseTodo() throws DukeException {
        if (fullLine.split("todo ").length <= 1) {
            String temp = new Ui().todoErrorString();
            throw new DukeException(temp);
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
        if (fullLine.split("/by ").length < 2) {
            throw new DukeException(new Ui().deadlineErrorString());
        }

        String longName = fullLine.split("/by ")[0];
        String date = fullLine.split("/by ")[1];
        String taskName = longName.split("deadline ")[1];
        String deadlineName = taskName
                + String.format("(by: %s)", date);

        return new Deadline(deadlineName);
    }

    /**
     * Returns an Event object with all relevant information.
     *
     * @return The Event object.
     * @throws DukeException If incorrect input.
     */
    public Event parseEvent() throws DukeException {
        if (fullLine.split("/").length < 3) {
            throw new DukeException(new Ui().eventErrorString());
        }
        String[] longNameArray = fullLine.split("/");
        String longName = longNameArray[0];
        String fromTime = longNameArray[1];
        fromTime = fromTime.split(" ")[1];
        String endTime = longNameArray[2];
        endTime = endTime.split(" ")[1];
        String taskName = longName.split("event ")[1];
        String eventName = taskName
                + String.format("(from: %s to: %s)",
                        fromTime, endTime);
        return new Event(eventName);
    }

    /**
     * Parses a given text input command and outputs a String.
     *
     * @param cmd The String command to be parsed.
     * @param tl The TaskList object to write tasks to.
     * @param store The Storage object to write tasks to file.
     * @return The String output of command parsing.
     * @throws DukeException For main program related errors.
     * @throws IOException For File-IO related errors.
     */
    public static String parseText(String cmd, TaskList tl, Storage store)
            throws DukeException, IOException {

        Parser p = new Parser(cmd);
        String command = p.command();
        String fullLine = p.fullLine;
        String finalOutput = "";
        Ui ui = new Ui();

        assert (command instanceof String);
        assert (command != null);

        switch (command) {
        case "bye":
            return "bye";

        case "list":
            finalOutput = CommandParser.listCommand(tl);
            break;

        case "mark":
            if (p.commandOnly()) {
                throw new DukeException(new Ui().indexErrorString());
            }
            tl.mark(p.num());
            try {
                store.taskListToFile(tl);
            } catch (IOException e) {
                throw new IOException(new Ui().fileErrorString());
            }

            finalOutput = ui.markPrinter(tl, p.num());

            break;

        case "unmark":
            if (p.commandOnly()) {
                throw new DukeException(new Ui().indexErrorString());
            }
            tl.unmark(p.num());
            try {
                store.taskListToFile(tl);
            } catch (IOException e) {
                throw new IOException(new Ui().fileErrorString());
            }

            finalOutput = ui.unmarkPrinter(tl, p.num());

            break;

        case "delete":
            if (p.commandOnly()) {
                throw new DukeException(new Ui().indexErrorString());
            }
            Task tempDelete = tl.get(p.num());
            tl.remove(p.num());
            try {
                store.taskListToFile(tl);
            } catch (IOException e) {
                throw new IOException(new Ui().fileErrorString());
            }

            finalOutput = ui.removedTaskScreen(tempDelete, tl.size());

            break;

        case "find":

            finalOutput = CommandParser.findCommand(p, tl);
            break;

        case "delete_all":
            tl.purgeList();
            try {
                store.taskListToFile(tl);
            } catch (IOException e) {
                finalOutput = ui.fileErrorString();
                break;
            }

            finalOutput = ui.removedAllTaskScreen();
            break;

        case "help":
            finalOutput = ui.startScreen();
            break;

        default:

            if (command.equals("todo")) {
                finalOutput = CommandParser.todoCommand(p, tl, store);

            } else if (command.equals("deadline")) {
                finalOutput = CommandParser.deadlineCommand(p, tl, store);

            } else if (command.equals("event")) {
                finalOutput = CommandParser.eventCommand(p, tl, store);

            } else {
                throw new DukeException(ui.unknownErrorString());
            }

            break;
        }

        assert (finalOutput != null);
        return finalOutput;

    }




}
