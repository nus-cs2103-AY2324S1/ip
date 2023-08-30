package data.task.builder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.task.Task;
import data.task.Todo;
import exception.DukeException;
import exception.InvalidInputException;

public class TodoBuilder implements Builder<Task> {
    private String pattern = "todo\\s+(.*)";
    
    /**
     * Builds a Todo object from a string.
     * @param input String input from user in the format "todo <description>".
     * @return Todo object.
     * @throws DukeException If input is invalid.
     */
    @Override
    public Todo buildFromString(String input) throws DukeException{
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        if (m.matches()) {
            String description = m.group(1);
            Todo todo = new Todo();
            todo.setDescription(description);
            todo.setUserInputString(input);
            return todo;
        }
        else {
            throw new InvalidInputException("expected format: todo <description>");
        }
    }
}

