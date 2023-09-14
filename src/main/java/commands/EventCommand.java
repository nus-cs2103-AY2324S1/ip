package commands;

import functions.TaskList;
import tasks.Event;

import java.time.LocalDateTime;

public class EventCommand extends Command {

    private TaskList taskList;
    private String functionDescription;

    public EventCommand(TaskList taskList, String functionDescription) {
        this.taskList = taskList;
        this.functionDescription = functionDescription;
    }

    @Override
    public String execute() {
        try {
            int fromDateStartIdx = functionDescription.indexOf("/from") + 6;
            int toDateStartIdx = functionDescription.indexOf("/to") + 4;
            int fromDateEndIdx = functionDescription.indexOf("/to") - 1;
            int descriptionStartIdx = 0;
            int descriptionEndIdx = functionDescription.indexOf("/from") - 1;

            String eventDescription = functionDescription.substring(descriptionStartIdx, descriptionEndIdx);
            String fromDateString = functionDescription.substring(fromDateStartIdx, fromDateEndIdx);
            String toDateString = functionDescription.substring(toDateStartIdx);

            LocalDateTime fromDate = parseDateTime(fromDateString);
            LocalDateTime toDate = parseDateTime(toDateString);

            boolean fromDateIsNull = fromDate == null;
            boolean toDateIsNull = toDate == null;

            if (fromDateIsNull || toDateIsNull) {
                return "Please input a date in the correct format.";
            }
            Event newEvent = new Event(eventDescription, fromDate, toDate);
            taskList.add(newEvent);
            String message = "Added: " + newEvent.getTaskAsString();
            return message;
        } catch (Exception e) {
            return "Sorry, I did not understand that. Please enter in the following format: \n" +
                    "event {description} /from {start datetime} /to {end datetime}.";
        }
    }
}
