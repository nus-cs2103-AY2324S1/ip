package duke.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exception.EmptyDescriptionException;
import duke.exception.MissingInformationException;

/**
 * A class that represents the Event class. It is 
 * a subtype of the abstract Task class.
 */

public class Todo extends Task {

    /**
     * Constructor of the Todo object
     * @param description description of Todo object
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * A factory method of Todo class.
     * String input is guaranteed to start with "todo"
     * @param input user input from terminal.
     * @return a Todo object given the input string.
     * @throws MissingInformationException due to possibly an empty
     * description of the todo task.
     */
    public static Todo of(String input) throws MissingInformationException {
        input = input.trim();
        if (input.length() <= 0) {
            throw new EmptyDescriptionException("todo");
        }
        try {
            Matcher matcher = Pattern.compile("todo ").matcher(input);
            matcher.find();
            String description = input.substring(matcher.end()).trim();
            return new Todo(description);
        } catch (Exception e) {
            throw new EmptyDescriptionException("todo");
        }
    }

    /**
     * A method that returns the string representation of a Todo object.
     * @return string representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * A method that returns the string representation of a Deadline object
     * @return string representation of the Deadline object to be stored in the database
     */
    @Override
    public String toBeStored() {
        String marked = this.isDone() ? "1" : "0";
        return "T | " + marked + " | " + this.getDescription() + "\n";
    }
}
