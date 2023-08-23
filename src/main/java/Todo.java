import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dukeExceptions.EmptyDescriptionException;
import dukeExceptions.MissingInformationException;

public class Todo extends Task {
    protected boolean isDone;

    public Todo(String description) {
        super(description);
    }

    public static Task of(String input) throws MissingInformationException {
        input = input.trim();
        if (input.length() <= 0) {
            throw new EmptyDescriptionException("todo");
        }
        try {
            Matcher matcher = Pattern.compile("todo ").matcher(input);
            if (!matcher.find()) {
                // return error
            }
            String description = input.substring(matcher.end()).trim();
            return new Todo(description);
        } catch (Exception e) {
            throw new EmptyDescriptionException("todo");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
