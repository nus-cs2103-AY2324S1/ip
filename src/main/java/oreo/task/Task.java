package oreo.task;

import oreo.exception.IllegalCommandException;
import oreo.exception.IllegalDateTimeException;
import oreo.datetime.TimeParser;

import java.util.Scanner;

/**
 * This class implements an abstract class, parent class for
 * the other specific task.
 *
 * @author Daniel Loh
 * @version 03/09/2023
 */
public abstract class Task implements Comparable<Task> {

    /**
     * id of the task.
     */
    protected int id;

    /**
     * description of the task.
     */
    protected String description;

    /**
     * whether task is complete.
     */
    protected boolean isComplete;
    
    private static int idTracker = 0;

    /**
     * Constructor of Task.
     *
     * @param d description of the task.
     */
    public Task(String d) {
        this.id = idTracker;
        idTracker++;
        this.description = d;
        this.isComplete = false;
    }

    /**
     * Generates the appropriate sub-task instance based on user input.
     *
     * @param command Command inputted by user.
     * @param tokeniser String that follows the command as a Scanner.
     * @return Approriate task base on input by user.
     * @throws IllegalCommandException when command is invalid.
     * @throws IllegalDateTimeException when date time input is invalid.
     */
    public static Task generateTask(String command, Scanner tokeniser) throws IllegalCommandException,
            IllegalDateTimeException {
        if (!tokeniser.hasNext()) {
            throw new IllegalCommandException("process an empty task");
        }

        String contents = tokeniser.nextLine();
        assert !contents.isEmpty(): "contents should not be empty";

        switch (command) {
        case "todo" :
            return processToDoCommand(contents);
        case "deadline":
            return processDeadlineCommand(contents);
        case "event" :
            return processEventCommand(contents);
        default:
            throw new IllegalCommandException("create such a task");
        }
    }

    private static Task processToDoCommand(String contents) throws IllegalCommandException {
        if (contents.contains("/by")) {
            throw new IllegalCommandException("do that for a todo, "
                    + "are you thinking of a deadline?");
        } else if (contents.contains("/from") || contents.contains("/to")) {
            throw new IllegalCommandException("do that for a todo,"
                    + "are you thinking of an event?");
        }
        return new ToDo(contents);
    }

    private static Task processDeadlineCommand(String contents) throws IllegalCommandException,
            IllegalDateTimeException {
        checkValidDeadlineFormat(contents); // catch invalid formats
        // Catch empty description and/or date
        String[] parts = contents.split(" /by ", 2);
        if (parts[0].isEmpty() || parts[0].isBlank()) {
            throw new IllegalCommandException("set a deadline without a description");
        } else if (parts[1].isEmpty() || parts[0].isBlank()) {
            throw new IllegalCommandException("do that, you might also want to include a date time");
        }
        String[] dateTime = TimeParser.parseInputOut(parts[1]);
        return new Deadline(parts[0], dateTime[0], dateTime[1]);
    }

    private static void checkValidDeadlineFormat(String contents)  throws IllegalCommandException,
            IllegalDateTimeException{
        if (!contents.contains(" /by ")) {
            if (contents.contains("/by")) {
                String[] parts = contents.split("/by");
                int size = parts.length;
                switch (size) {
                case 1:
                    if (parts[0].isBlank()){
                        throw new IllegalCommandException("do that without a task description and date time");
                    }
                    throw new IllegalCommandException("do that, you might also want to include a date time");
                default:
                    throw new IllegalCommandException("do that, "
                            + "seems like you may have joined the \"by\" with the date time or unecessary characters. "
                            + "Do leave space around \"by\"");
                }
            }
            throw new IllegalCommandException("set a deadline without a \"/by\",\n"
                    + "or was there a typo?");
        } else if (contents.contains("/from") || contents.contains("/to")) {
            throw new IllegalCommandException("do that for a deadline,"
                    + "are you thinking of an event?");
        }
    }

    private static Task processEventCommand(String contents) throws IllegalCommandException,
            IllegalDateTimeException {
        checkValidEventFormat(contents); // catch invalid formats
        // Catch empty description and/or date
        String[] message = contents.split(" /from ", 2);
        if (message[0].isEmpty()) {
            throw new IllegalCommandException("process an event without a description");
        }
        String[] fromto = message[1].split(" /to ", 2);
        String[] fromDateTime = TimeParser.parseInputOut(fromto[0]);
        String[] toDateTime = TimeParser.parseInputOut(fromto[1]);
        return new Event(message[0], fromDateTime[0], fromDateTime[1],
                toDateTime[0], toDateTime[1]);
    }

    private static void checkValidEventFormat(String contents)  throws IllegalCommandException,
            IllegalDateTimeException{
        if (!contents.contains(" /from ") || !contents.contains(" /to ")) {
            if (contents.contains("/from") && contents.contains("/to")) {
                throw new IllegalCommandException("do that, there might be some formatting "
                        + "errors in your input, it should look like\n"
                        + "\"event <description> /from <datetime> /to <datetime>\".");
            }
            throw new IllegalCommandException("set an event without a \"/from\" and/or \"/to\",\n");
        } else if (contents.contains("/by")) {
            throw new IllegalCommandException("do that for an event,"
                    + "are you thinking of an deadline?");
        }
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    /**
     * Change mark to opposite of the current status.
     */
    public void switchMark() {
        isComplete = !isComplete;
    }

    /**
     * Generates appropriate task from file data.
     *
     * @param id determines type of task.
     * @param mark determines if task is done.
     * @param description contains description of task.
     * @return Appropriate task base on file input.
     * @throws IllegalDateTimeException date time format is invalid,
     *                                  file format is corrupt.
     */
    public static Task generateSavedTask(int id, boolean mark, String description) throws IllegalDateTimeException {
        switch (id) {
        case (1):
            return new ToDo(description, mark);
        case (2):
            String[] parts = description.split("/", 2);
            String[] dateTime = TimeParser.parseInputOut(parts[1]);
            return new Deadline(parts[0], dateTime[0], dateTime[1], mark);
        default:
            String[] message = description.split("/", 3);
            String[] fromDateTime = TimeParser.parseInputOut(message[1]);
            String[] toDateTime = TimeParser.parseInputOut(message[2]);
            return new Event(message[0], fromDateTime[0], fromDateTime[1],
                    toDateTime[0], toDateTime[1], mark);
        }
    }

    /**
     * Converts task to file data to write to file.
     *
     * @return task in file data format.
     */
    public abstract String writeToFile();

    public abstract String getTaskInEditFormat();

    /**
     * Checks if description contains keyword
     * @param keyword String of interest
     * @return true if description contains String
     */
    public boolean contains(String keyword) {
        return description.contains(keyword);
    }

    @Override
    public abstract String toString();

    @Override
    public int compareTo(Task other) {
        return this.id - other.id;
    }
}
