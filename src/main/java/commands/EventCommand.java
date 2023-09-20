package commands;

import java.time.LocalDateTime;

import functions.TaskList;
import tasks.Event;
import tasks.Task;

/**
 * The class for executing an addition command of an event task
 */
public class EventCommand extends Command {

    private TaskList taskList;
    private String functionDescription;

    /**
     * Constructs a new EventCommand object with the specified task list and function description.
     *
     * @param taskList The task list to add the event task to.
     * @param functionDescription The description of the event task to be added.
     */
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

            boolean isFromDateNull = fromDate == null;
            boolean isToDateNull = toDate == null;

            if (isFromDateNull || isToDateNull) {
                return "Please input a date in the correct format.";
            }

            if (toDate.isBefore(fromDate)) {
                return "Error adding event to task list. The start date cannot be after end date!";
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
            return "Sorry, I did not understand that. Please enter in the following format: \n"
                    + "event {description} /from {start datetime} /to {end datetime}.";
        }
    }

    /**
     * Parses a String format of datetime into a LocalDateTime object.
     *
     * @param newEvent an Event for comparison
     * @param fromDate start date of event
     * @param toDate end date of event
     *
     * @return a TaskList consisting of all the events that clashes with the one given in input
     */
    public TaskList checkClash(Event newEvent, LocalDateTime fromDate, LocalDateTime toDate) {

        TaskList clashingTasks = new TaskList();

        for (Task task: this.taskList.getTaskList()) {
            if (!(task instanceof Event) || task.equals(newEvent)) {
                continue;
            }

            Event event = (Event) task;

            LocalDateTime startDate = parseDateTime(event.getStartDate());
            LocalDateTime endDate = parseDateTime(event.getEndDate());

            boolean isBeforeEventEnd = fromDate.isBefore(endDate);
            boolean isAfterEventStart = toDate.isAfter(startDate);

            if (isBeforeEventEnd && isAfterEventStart) {
                clashingTasks.add(event);
            }
        }

        return clashingTasks;
    }
}
