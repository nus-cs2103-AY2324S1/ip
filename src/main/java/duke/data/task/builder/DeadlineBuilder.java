package duke.data.task.builder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.data.task.Deadline;
import duke.data.task.Task;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;

public class DeadlineBuilder implements Builder<Task> {
    private String pattern = "deadline\\s+(.*?)\\s+/by\\s+(.*)";
    
    /**
     * Builds a Deadline object from a string.
     * @param input String input from user in the format "deadline <description> /by YYYY-MM-DD HH:MM".
     * @return Deadline object.
     * @throws DukeException If input is invalid.
     */
    @Override
    public Task buildFromString(String input) throws DukeException {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        if (m.matches()) {
            String description = m.group(1);
            String by = m.group(2);
            Deadline deadline = new Deadline();
            deadline.setDescription(description);
            deadline.setBy(by);
            deadline.setUserInputString(input);
            return deadline;
        } else {
            throw new InvalidInputException("expected format: deadline <description> /by YYYY-MM-DD HH:MM");
        }

    }
}
