package tasks;

import dogebot.DateTimeHandler;

/**
 * The Event class stores the description of an event task and its start and end date/time.
 *
 * @author Kenvyn Kwek
 */
public class Event extends Task {
    private DateTimeHandler start;
    private DateTimeHandler end;
    private DateTimeHandler reminder;

    /**
     * Initializes an event task.
     *
     * @param description Description of the event task.
     * @param start Start date and time.
     * @param end End date and time.
     * @param isDone If the task has already been completed.
     */
    public Event(String description, String start, String end, boolean isDone, String reminder) {
        super(description, isDone, reminder);
        this.start = new DateTimeHandler(start);
        this.end = new DateTimeHandler(end);
    }

    /**
     * Empty constructor.
     */
    public Event() {}

    /**
     * String representation of an event task.
     *
     * @return Event task details.
     */
    @Override
    public String toString() {
        String reminderString = (super.reminder == null) ? "" : " | Reminder: " + super.reminder.toString();
        return "E | " + super.toString() + " | " + start.toString() + " - " + end.toString() + reminderString;
    }

    /**
     * Processes command and returns parameter inputs for constructors.
     *
     * @param input Input command.
     * @return String array of parameter inputs.
     */
    @Override
    public String[] processInput(String input) {
        String taskDescription = input.split("event ")[1].split(" /from")[0];
        String start = input.split("/from ")[1].split(" /to")[0];
        String end = input.split("/to ")[1];

        String reminderDate = null;
        if (input.contains("r/")) {
            String daysEarly = input.split("r/")[1];
            reminderDate = processReminderDate(daysEarly, start);
        }

        return new String[] {taskDescription, start, end, reminderDate};
    }
}
