package duke.data.task.builder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.data.task.Event;
import duke.data.task.Task;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;

public class EventBuilder implements Builder<Task> {
    private String pattern = "event\\s+(.*?)\\s+/from\\s+(.*?)\\s+/to\\s+(.*)";
    
    /**
     * Builds an Event object from a string.
     * @param input String input from user in the format "event <description> /from <start> /to <end>".
     * @return Event object.
     * @throws DukeException If input is invalid.
     */
    @Override
    public Task buildFromString(String input) throws DukeException {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        if (m.matches()) {
            String description = m.group(1);
            String start = m.group(2);
            String end = m.group(3);
            Event event = new Event();
            event.setDescription(description);
            event.setFrom(start);
            event.setTo(end);
            event.setUserInputString(input);
            return event;
        } else {
            throw new InvalidInputException("expected format: event <description> /from <start> /to <end>");
        }

    }

}
