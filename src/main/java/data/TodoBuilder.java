package data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.DukeException;
import exception.InvalidInputException;

public class TodoBuilder implements Builder<Task> {
    private String pattern = "todo\\s+(.*)";
    

    @Override
    public Todo buildFromString(String input) throws DukeException{
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        if (m.matches()) {
            String description = m.group(1);
            Todo todo = new Todo();
            todo.setDescription(description);
            return todo;
        }
        else {
            throw new InvalidInputException("expected format: todo <description>");
        }
    }
}

