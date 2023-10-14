package shiba.tasks;

import shiba.exceptions.InvalidCommandException;
import shiba.parsers.SpaceSeparatedValuesParser;

/**
 * Represents a task with no date, time constraints.
 */
public class TodoTask extends ShibaTask {
    /**
     * Creates a TodoTask.
     *
     * @param name The name of the task.
     */
    public TodoTask(String name) {
        super(name, TaskType.TODO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSaveString() {
        return SpaceSeparatedValuesParser.convert("T", isDone ? "1" : "0", name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Parses a TodoTask from a command.
     *
     * @param cmd The command to be parsed.
     * @return The TodoTask parsed from the command, or null if the command is invalid.
     * @throws InvalidCommandException If the command is invalid.
     */
    public static TodoTask fromCmd(String cmd) throws InvalidCommandException {
        String[] cmdSplit = cmd.split(" ", 2);

        if (!cmdSplit[0].toUpperCase().equals(TaskType.TODO.name())) {
            throw new InvalidCommandException("Expected todo command!");
        }

        if (cmdSplit.length != 2) {
            throw new InvalidCommandException("Todo name should not be empty!");
        }

        return new TodoTask(cmdSplit[1]);
    }
}
