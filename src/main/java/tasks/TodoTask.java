package tasks;

import exceptions.InvalidCommandException;
import parsers.SpaceSeparatedValuesParser;

public class TodoTask extends ShibaTask {
    /**
     * Parses a TodoTask from a command.
     *
     * @param cmd The command to be parsed.
     * @return The TodoTask parsed from the command, or null if the command is invalid.
     */
    public static TodoTask fromCmd(String cmd) throws InvalidCommandException {
        String[] cmdSplit = cmd.split(" ", 2);
        if (cmdSplit.length != 2) {
            throw new InvalidCommandException("Todo name should not be empty!");
        }

        return new TodoTask(cmdSplit[1]);
    }

    public TodoTask(String name) {
        super(name, TaskType.TODO);
    }

    @Override
    public String toSaveString() {
        return SpaceSeparatedValuesParser.convert("T", isDone ? "1" : "0", name);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
