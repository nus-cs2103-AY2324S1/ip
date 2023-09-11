package duke.data.task.builder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.data.task.Task;
import duke.data.task.Todo;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;

public class TodoBuilder implements Builder<Task> {
    private String pattern = "todo\\s+(.*)";
    
    /**
     * Builds a Todo object from a string.
     * @param input String input from user in the format "todo <description>".
     * @return Todo object.
     * @throws DukeException If input is invalid.
     */
    @Override
    public Todo buildFromString(String input) throws DukeException {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        if (m.matches()) {
            String description = m.group(1);
            Todo todo = new Todo();
            todo.setDescription(description);
            todo.setUserInputString(input);
            return todo;
        } else {
            throw new InvalidInputException("expected format: todo <description>");
        }
    }
}
