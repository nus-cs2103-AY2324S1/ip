package com.ducky.util;

import com.ducky.command.AddTaskCommand;
import com.ducky.command.DuckyInvalidCommandFormatException;
import com.ducky.command.UpdateTaskCompletionCommand;
import com.ducky.task.TaskType;

/**
 * Handles the validation and packaging of parsed commands into command objects.
 */
public class ParsedCommandHandler {
    public static final String INVALID_EVENT_FORMAT_ERROR_MSG = "A description, start time and end time is required for creating an event.";
    private static final String INVALID_NUMBER_ERROR_MSG =
            "Did you enter a valid number?";
    private static final String INVALID_TODO_FORMAT_ERROR_MSG =
            "A description is required for creating a to-do.";
    private static final String INVALID_DEADLINE_FORMAT_ERROR_MSG =
            "A description and deadline (in yyyy-mm-dd format) is required for creating a deadline.";

    /**
     * Validates event addition command parsed by parser and repackages it into a command object.
     *
     * @param argumentString Parsed argument string.
     * @return Repackaged command object for adding parsed event.
     * @throws DuckyInvalidCommandFormatException If the command is not in the correct format.
     */
    static AddTaskCommand handleAddEventTask(String argumentString) throws DuckyInvalidCommandFormatException {
        String[] eventParts = argumentString.split("/from|/to", 3);
        // Check if there are 3 arguments
        if (eventParts.length < 3) {
            throw new DuckyInvalidCommandFormatException(INVALID_EVENT_FORMAT_ERROR_MSG);
        }
        // Check all 3 arguments are not empty
        for (int i = 0; i < eventParts.length; i++) {
            eventParts[i] = eventParts[i].trim();
            if (eventParts[i].isEmpty()) {
                throw new DuckyInvalidCommandFormatException(INVALID_EVENT_FORMAT_ERROR_MSG);
            }
        }

        return new AddTaskCommand(
                TaskType.EVENT,
                eventParts[0],
                eventParts[1],
                eventParts[2]
        );
    }

    /**
     * Validates deadline addition command parsed by parser and repackages it into a command object.
     *
     * @param argumentString Parsed argument string.
     * @return Repackaged command object for adding parsed deadline.
     * @throws DuckyInvalidCommandFormatException If the command is not in the correct format.
     */
    static AddTaskCommand handleAddDeadlineTask(String argumentString) throws DuckyInvalidCommandFormatException {
        String[] deadlineParts = argumentString.split("/by", 2);
        // Check if there are 2 arguments
        if (deadlineParts.length < 2) {
            throw new DuckyInvalidCommandFormatException(INVALID_DEADLINE_FORMAT_ERROR_MSG);
        }
        // Check both arguments are not empty
        for (int i = 0; i < deadlineParts.length; i++) {
            deadlineParts[i] = deadlineParts[i].trim();
            if (deadlineParts[i].isEmpty()) {
                throw new DuckyInvalidCommandFormatException(INVALID_DEADLINE_FORMAT_ERROR_MSG
                );
            }
        }

        return new AddTaskCommand(TaskType.DEADLINE, deadlineParts[0], deadlineParts[1]);
    }

    /**
     * Validates todotask addition command parsed by parser and repackages it into a command object.
     *
     * @param argumentString Parsed argument string.
     * @return Repackaged command object for adding parsed todotask.
     * @throws DuckyInvalidCommandFormatException If the command is not in the correct format.
     */
    static AddTaskCommand handleAddTodoTask(String argumentString) throws DuckyInvalidCommandFormatException {
        // If description argument is empty, throw exception
        if (argumentString.isEmpty()) {
            throw new DuckyInvalidCommandFormatException(
                    INVALID_TODO_FORMAT_ERROR_MSG
            );
        }

        return new AddTaskCommand(TaskType.TODO, argumentString);
    }

    /**
     * Validates task completion status update command parsed by parser and repackages it into command object.
     *
     * @param argumentString Parsed argument string.
     * @param isCompleted Whether the specified task is complete.
     * @return Repackaged command object for updating task completion.
     * @throws DuckyInvalidCommandFormatException If the given index is not a valid number.
     */
    static UpdateTaskCompletionCommand handleUpdateTaskCompletion(String argumentString, boolean isCompleted) throws DuckyInvalidCommandFormatException {
        int markInputIndex;
        try {
            markInputIndex = Integer.parseInt(argumentString);
        } catch (NumberFormatException e) {
            throw new DuckyInvalidCommandFormatException(INVALID_NUMBER_ERROR_MSG);
        }
        return new UpdateTaskCompletionCommand(markInputIndex, isCompleted);
    }
}
