package commands;

import functions.TaskList;
import tasks.Event;

import java.time.LocalDateTime;

public class EventCommand extends Command {

    private TaskList taskList;
    private String secondHalfInput;

    public EventCommand(TaskList taskList, String secondHalfInput) {
        this.taskList = taskList;
        this.secondHalfInput = secondHalfInput;
    }

    @Override
    public void execute() {
        try {
            int fromDateStartIdx = secondHalfInput.indexOf("/from") + 6;
            int toDateStartIdx = secondHalfInput.indexOf("/to") + 4;
            int fromDateEndIdx = secondHalfInput.indexOf("/to") - 1;
            int descriptionStartIdx = 0;
            int descriptionEndIdx = secondHalfInput.indexOf("/from") - 1;
            String eventDescription = secondHalfInput.substring(descriptionStartIdx, descriptionEndIdx);
            String fromDateString = secondHalfInput.substring(fromDateStartIdx, fromDateEndIdx);
            String toDateString = secondHalfInput.substring(toDateStartIdx);
            LocalDateTime fromDate = parseDateTime(fromDateString);
            LocalDateTime toDate = parseDateTime(toDateString);
            if (fromDate == null || toDate == null) {
                return;
            }
            Event newEvent = new Event(eventDescription, fromDate, toDate);
            taskList.add(newEvent);
            System.out.println("Added: " + newEvent.getTaskAsString());
        } catch (Exception e) {
            System.out.println("Sorry, I did not understand that. Please enter in the following format: \n" +
                    "event {description} /from {start datetime} /to {end datetime}.");
        }
    }
}
