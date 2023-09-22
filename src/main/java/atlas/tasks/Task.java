package atlas.tasks;

import java.time.LocalDate;

import atlas.components.Parser;

/**
 * A Task is an object with a name and toggleable status
 */
public abstract class Task {
    protected final String name;
    protected boolean isDone;
    protected final LocalDate reminderStartDate;

    /**
     * Constructs a new Task with description, but with no reminders
     * @param name Name of task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
        this.reminderStartDate = null;
    }

    /**
     * Constructs a new Task with description and reminders
     * @param name Name of task
     * @param reminderStartDate Date starting from which reminders should be sent
     */
    public Task(String name, LocalDate reminderStartDate) {
        this.name = name;
        this.isDone = false;
        this.reminderStartDate = reminderStartDate;
    }

    /**
     * Returns icon indicating task's status
     * @return "X" if task is done, "   " otherwise
     */
    private String getStatusIcon() {
        final String taskDoneIcon = "X";
        final String taskNotDoneIcon = "  ";
        return this.isDone ? taskDoneIcon : taskNotDoneIcon;
    }

    public String getName() {

        return name;
    }

    public boolean getTaskStatus() {
        return isDone;
    }

    /**
     * Marks task as done
     * @return Task that has been marked
     */
    public Task markDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Marks task as not done
     * @return Task that has been unmarked
     */
    public Task markNotDone() {
        this.isDone = false;
        return this;
    }

    @Override
    public String toString() {
        if (hasReminder()) {
            assert reminderStartDate != null;
            return String.format("[%s] %s (remind starting from: %s)", getStatusIcon(),
                    getName(), reminderStartDate.format(Parser.DATE_FORMATTER));
        }
        return String.format("[%s] %s", getStatusIcon(), getName());
    }

    /**
     * Returns string representation to save to file
     * @return String representation written to file
     */
    public abstract String generateSaveString();

    /**
     * Returns whether the task occurs on a given date
     * @param date Date specified
     */
    public boolean isOccurringOnDate(LocalDate date) {
        return false;
    }

    /**
     * Returns whether the task's name contains the keyword
     * @param keyword Keyword to query for
     * @return True if it contains the keyword, false otherwise
     */
    public boolean hasKeyword(String keyword) {
        return name.contains(keyword);
    }

    /**
     * Returns whether the user should be reminded about this task
     * @return True if user should be reminded, false otherwise
     */
    public boolean shouldSendReminder() {
        if (hasReminder()) {
            boolean isTaskUncompleted = !isDone;
            LocalDate today = LocalDate.now();
            assert reminderStartDate != null;
            boolean isTimeForReminders = !reminderStartDate.isAfter(today);

            return isTaskUncompleted && isTimeForReminders;
        }
        return false;
    }

    /**
     * Returns whether the user has set a reminder for this task
     * @return True if a reminder has been set, false otherwise
     */
    protected boolean hasReminder() {
        return reminderStartDate != null;
    }
}
