package commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import functions.TaskList;
import tasks.Event;

/**
 * Represents a command to load a event task into a task list.
 */
public class LoadEventCommand extends Command {

    private String currentTaskAsString;
    private TaskList taskList;

    /**
     * Constructs a new LoadEventCommand object with the given parameters.
     *
     * @param currentTaskAsString a string representation of the event task to be loaded
     * @param taskList the task list to which the event task should be added
     */
    public LoadEventCommand(String currentTaskAsString, TaskList taskList) {
        this.currentTaskAsString = currentTaskAsString;
        this.taskList = taskList;
    }

    @Override
    public String execute() {
        boolean isDone = currentTaskAsString.substring(4, 5).toUpperCase().equals("X");
        int descriptionBeginIndex = 7;

        int descriptionEndIndex = currentTaskAsString.indexOf("(from:") - 1;
        int fromTimingStartIndex = currentTaskAsString.indexOf("(from:") + 7;
        int fromTimingEndIndex = currentTaskAsString.indexOf("to:") - 1;
        int toTimingStartIndex = fromTimingEndIndex + 5;
        int toTimingEndIndex = currentTaskAsString.length() - 1;

        String description = currentTaskAsString.substring(descriptionBeginIndex, descriptionEndIndex);
        String fromString = currentTaskAsString.substring(fromTimingStartIndex, fromTimingEndIndex);
        String toString = currentTaskAsString.substring(toTimingStartIndex, toTimingEndIndex);

        LocalDateTime fromDateTime = null;
        LocalDateTime toDateTime = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            fromDateTime = LocalDateTime.parse(fromString, formatter);
            toDateTime = LocalDateTime.parse(toString, formatter);
        } catch (Exception e) {
            return "tasks.Event " + description + " cannot be loaded.";
        }

        Event event = new Event(description, fromDateTime, toDateTime, isDone);
        taskList.add(event);

        return "Ok";
    }
}
