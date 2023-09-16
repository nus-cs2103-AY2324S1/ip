package commands;

import functions.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The class for executing an addition command of an event task
 */
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

            TaskList clashingTasks = checkClash(newEvent, fromDate, toDate);

            String message = "";

            if (clashingTasks.size() > 0) {
                message += "Please take note that you have the following tasks in conflict: \n";
                message += new ListCommand(clashingTasks).execute();
                message += "\n";
            }

            message += "Added: " + newEvent.getTaskAsString();
            return message;
        } catch (Exception e) {
            return "Sorry, I did not understand that. Please enter in the following format: \n" +
                    "event {description} /from {start datetime} /to {end datetime}.";
        }
    }

    public TaskList checkClash(Event newEvent, LocalDateTime fromDate, LocalDateTime toDate) {

        TaskList clashingTasks = new TaskList();

        for (Task task: this.taskList.getTaskList()) {
            if (!(task instanceof Event) || task.equals(newEvent)) {
                continue;
            }

            Event event = (Event) task;

            LocalDateTime startDate = parseDateTime(event.getStartDate());
            LocalDateTime endDate =  parseDateTime(event.getEndDate());

            boolean isBeforeEventEnd = fromDate.isBefore(endDate);
            boolean isAfterEventStart = toDate.isAfter(startDate);

            if (isBeforeEventEnd && isAfterEventStart) {
                clashingTasks.add(event);
            }
        }

        return clashingTasks;
    }
}
