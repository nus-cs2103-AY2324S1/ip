import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dukeExceptions.EmptyDescriptionException;
import dukeExceptions.MissingInformationException;

/*
 * A class that is represents the Event class. It is 
 * a subtype of the abstract Task class.
 */

public class Todo extends Task {
    protected boolean isDone;

    public Todo(String description) {
        super(description);
    }

    /*
     * A factory method of Todo class.
     * String input is guaranteed to start with "todo"
     * 
     * @param input user input from terminal.
     * 
     * @return a Todo object given the input string.
     * 
     * @throws MissingInformationException due to possibly an empty
     * description of the todo task.
     */
    public static Task of(String input) throws MissingInformationException {
        input = input.trim();
        if (input.length() <= 0) {
            throw new EmptyDescriptionException("todo");
        }
        try {
            Matcher matcher = Pattern.compile("todo ").matcher(input);
            String description = input.substring(matcher.end()).trim();
            return new Todo(description);
        } catch (Exception e) {
            throw new EmptyDescriptionException("todo");
        }
    }

    /*
     * A method that returns the string representation of a Todo object.
     * 
     * @return string representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
