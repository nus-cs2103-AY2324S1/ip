package data.task.builder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.task.Event;
import data.task.Task;
import exception.DukeException;
import exception.InvalidInputException;

public class EventBuilder implements Builder<Task> {
    private String pattern = "event\\s+(.*?)\\s+/from\\s+(.*?)\\s+/to\\s+(.*)";

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
