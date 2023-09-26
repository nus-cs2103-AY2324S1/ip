package commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import functions.TaskList;
import tasks.Deadline;

/**
 * Represents a command to load a deadline task into a task list.
 */
public class LoadDeadlineCommand extends Command {

    private String currentTaskAsString;
    private TaskList taskList;

    /**
     * Constructs a new LoadDeadlineCommand object with the given parameters.
     *
     * @param currentTaskAsString a string representation of the deadline task to be loaded
     * @param taskList the task list to which the deadline task should be added
     */
    public LoadDeadlineCommand(String currentTaskAsString, TaskList taskList) {
        this.currentTaskAsString = currentTaskAsString;
        this.taskList = taskList;
    }

    @Override
    public String execute() {
        boolean isDone = currentTaskAsString.substring(4, 5).toUpperCase().equals("X");
        int descriptionBeginIndex = 7;

        int descriptionEndIndex = currentTaskAsString.indexOf("(by:") - 1;
        int deadlineStartIndex = currentTaskAsString.indexOf("(by:") + 5;
        String description = currentTaskAsString.substring(descriptionBeginIndex, descriptionEndIndex);
        String deadlineTimeString = currentTaskAsString.substring(deadlineStartIndex, currentTaskAsString.length() - 1);
        LocalDateTime deadlineTime = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            deadlineTime = LocalDateTime.parse(deadlineTimeString, formatter);
        } catch (Exception e) {
            return "tasks.Deadline " + description + " cannot be loaded.";
        }
        Deadline deadline = new Deadline(description, deadlineTime, isDone);
        taskList.add(deadline);

        return "Ok";
    };

}
