package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dogebot.DateTimeHandler;

/**
 * The Task class stores the description of a task.
 *
 * @author Kenvyn Kwek
 */
public class Task {
    protected DateTimeHandler reminder;
    private String description;
    private boolean isDone;

    /**
     * Initializes a task.
     *
     * @param description Task description.
     * @param isDone If the task has already been completed.
     */
    public Task(String description, boolean isDone, String reminder) {
        this.description = description;
        this.isDone = isDone;
        this.reminder = (reminder == null) ? null : new DateTimeHandler(reminder);
    }

    /**
     * Empty constructor.
     */
    public Task() {
    }

    /**
     * String representation of a task.
     *
     * @return Details of a task.
     */
    public String toString() { // encapsulation principle
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Marks or unmarks a task.
     *
     * @param mark If the task is completed.
     */
    public void markTask(boolean mark) {
        isDone = (mark) ? true : false;
    }

    /**
     * Checks if tasks description contains the input word.
     *
     * @param s Word to search in task description.
     * @return If the task description contains the input word.
     */
    public boolean hasWord(String s) {
        return (description.contains(s)) ? true : false;
    }

    /**
     * Checks if task has an upcoming reminder date.
     *
     * @return If the task has an upcoming reminder date.
     */
    public boolean hasUpcomingReminder() {
        if (reminder == null) {
            return false;
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy K:mma");
        String formattedNow = now.format(formatter);
        LocalDateTime curr = LocalDateTime.parse(formattedNow, formatter);
        return reminder.currentDatePassed(curr);
    }

    /**
     * Processes command and returns parameter inputs for constructors.
     *
     * @param input Input command.
     * @return String array of parameter inputs.
     */
    public String[] processInput(String input) {
        return new String[0];
    }

    /**
     * Processes user input into reminder date.
     *
     * @param reminder Number of days in advance to remind.
     * @param taskDate Date of the task.
     * @return Processed reminder date.
     */
    public String processReminderDate(String reminder, String taskDate) {
        int daysEarly = Integer.parseInt(reminder);
        String[] split = taskDate.split("/");
        int taskDay = Integer.parseInt(split[0]);
        int taskMonth = Integer.parseInt(split[1]);

        int reminderDay = taskDay - daysEarly;
        int reminderMonth = taskMonth;

        if (reminderDay <= 0) {
            reminderMonth--;

            switch (reminderMonth) {
            case 2:
                reminderDay = 28 - Math.abs(reminderDay);
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                reminderDay = 30 - Math.abs(reminderDay);
                break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                reminderDay = 31 - Math.abs(reminderDay);
                break;
            default:
                break;
            }
        }
        String toReplace = taskDay + "/" + taskMonth;
        String replaceWith = reminderDay + "/" + reminderMonth;
        String result = taskDate.replaceFirst(toReplace, replaceWith);
        return result;
    }
}
