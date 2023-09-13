package duke.commands;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Represents a command to be executed.
 */
public class Command {
    private final String commandType;
    private Optional<String> description = Optional.empty();
    private Optional<Integer> taskIndex = Optional.empty();
    private Optional<LocalDate> deadlineDate = Optional.empty();
    private Optional<LocalDate> eventFromDate = Optional.empty();
    private Optional<LocalDate> eventToDate = Optional.empty();

    private Command(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Creates a command with the given command type
     * e.g. "bye", "list"
     *
     * @param commandType The command type.
     * @return The command.
     */
    public static Command of(String commandType) {
        return new Command(commandType);
    }

    /**
     * Creates a command with the given command type and description
     * e.g. "todo", "find"
     *
     * @param commandType The command type.
     * @param description The description.
     * @return The command.
     */
    public static Command ofDescription(String commandType, String description) {
        Command command = new Command(commandType);
        command.description = Optional.ofNullable(description);
        return command;
    }

    /**
     * Creates a command with the given command type and task index
     * e.g. "delete", "mark"
     *
     * @param commandType The command type.
     * @param taskIndex The task index.
     * @return The command.
     */
    public static Command ofTaskIndex(String commandType, int taskIndex) {
        Command command = new Command(commandType);
        command.taskIndex = Optional.of(taskIndex);
        return command;
    }

    public static Command ofDeadline(String commandType, String description, LocalDate deadlineDate) {
        Command command = ofDescription(commandType, description);
        command.deadlineDate = Optional.ofNullable(deadlineDate);
        return command;
    }

    public static Command ofEvent(String commandType, String description, LocalDate eventFromDate, LocalDate eventToDate) {
        Command command = ofDescription(commandType, description);
        command.eventFromDate = Optional.ofNullable(eventFromDate);
        command.eventToDate = Optional.ofNullable(eventToDate);
        return command;
    }

    public Optional<Integer> getTaskIndex() {
        return taskIndex;
    }

    public String getCommandType() {
        return commandType;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public Optional<LocalDate> getDeadlineDate() {
        return deadlineDate;
    }

    public Optional<LocalDate> getEventFromDate() {
        return eventFromDate;
    }

    public Optional<LocalDate> getEventToDate() {
        return eventToDate;
    }
}
