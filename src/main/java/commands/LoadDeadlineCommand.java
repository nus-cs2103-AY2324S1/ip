package commands;

import functions.TaskList;
import tasks.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoadDeadlineCommand extends Command {

    private String currentTaskAsString;
    private TaskList taskList;

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
