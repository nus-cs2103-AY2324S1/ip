package duke.commands;

import java.time.LocalDate;

/**
 * Represents a command to be executed.
 */
public class Command {
    private final String commandType;
    private String description;
    private int taskIndex;
    private LocalDate deadlineDate;
    private LocalDate eventFromDate;
    private LocalDate eventToDate;

    public Command(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Initializes a command with the given command type and description.
     *
     * @param commandType The command type.
     * @param description The description.
     */
    public Command(String commandType, String description) {
        this.commandType = commandType;
        this.description = description;
    }

    /**
     * Initializes a command with the given command type and task index.
     *
     * @param commandType The command type.
     * @param taskIndex The task index.
     */
    public Command(String commandType, int taskIndex) {
        this.commandType = commandType;
        this.taskIndex = taskIndex;
    }

    /**
     * Initializes a command with the given command type, description and deadline date.
     *
     * @param commandType The command type.
     * @param description The description.
     * @param deadlineDate The deadline date.
     */
    public Command(String commandType, String description, LocalDate deadlineDate) {
        this.commandType = commandType;
        this.description = description;
        this.deadlineDate = deadlineDate;
    }

    /**
     * Initializes a command with the given command type, description and event dates.
     *
     * @param commandType The command type.
     * @param description The description.
     * @param eventFromDate The event from date.
     * @param eventToDate The event to date.
     */
    public Command(String commandType, String description, LocalDate eventFromDate, LocalDate eventToDate) {
        this.commandType = commandType;
        this.description = description;
        this.eventFromDate = eventFromDate;
        this.eventToDate = eventToDate;
    }

    public int getTaskIndex() {
        return this.taskIndex;
    }
    public String getCommandType() {
        return this.commandType;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getDeadlineDate() {
        return this.deadlineDate;
    }

    public LocalDate getEventFromDate() {
        return this.eventFromDate;
    }

    public LocalDate getEventToDate() {
        return this.eventToDate;
    }
}
