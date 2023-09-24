package tasks;

import dogebot.DateTimeHandler;

/**
 * The Deadline class stores the task description and its deadline.
 *
 * @author Kenvyn Kwek
 */
public class Deadline extends Task {
    private DateTimeHandler by;

    /**
     * Initializes a deadline task.
     *
     * @param description Deadline task description.
     * @param by Deadline to complete the task by.
     * @param isDone If the task has already been completed.
     */
    public Deadline(String description, String by, boolean isDone, String reminder) {
        super(description, isDone, reminder);
        this.by = new DateTimeHandler(by);
    }

    /**
     * Empty constructor.
     */
    public Deadline() {
    }

    /**
     * String representation of a deadline task.
     *
     * @return Deadline task details.
     */
    @Override
    public String toString() {
        String reminderString = (super.reminder == null) ? "" : " | Reminder: " + super.reminder.toString();
        return "D | " + super.toString() + " | " + by.toString() + reminderString;
    }

    /**
     * Processes command and returns parameter inputs for constructors.
     *
     * @param input Input command.
     * @return String array of parameter inputs.
     */
    @Override
    public String[] processInput(String input) {
        String taskDescription = input.split("deadline ")[1].split(" /by")[0];
        String taskDeadline = input.split("/by ")[1];

        String reminderDate = null;
        if (input.contains("r/")) {
            String daysEarly = input.split("r/")[1];
            reminderDate = processReminderDate(daysEarly, taskDeadline);
        }

        return new String[] {taskDescription, taskDeadline, reminderDate};
    }
}
