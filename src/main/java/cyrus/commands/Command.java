package cyrus.commands;

import cyrus.parser.ParseInfo;
import cyrus.tasks.TaskList;

/**
 * Represents a general command to operate on a {@code TaskList} and a given {@code ParseInfo}
 */
public abstract class Command {
    /**
     * {@code TaskList} made {@code protected final} since sub-classes should be able to access it.
     */
    protected final TaskList taskList;
    /**
     * {@code ParseInfo} made {@code protected final} since sub-classes should be able to access it.
     */
    protected final ParseInfo parseInfo;

    /**
     * Base constructor for a type of command.
     *
     * @param taskList  {@code TaskList} with available tasks to operate on.
     * @param parseInfo parsed information from {@code Parser}.
     */
    public Command(TaskList taskList, ParseInfo parseInfo) {
        this.taskList = taskList;
        this.parseInfo = parseInfo;
    }

    /**
     * Behavior of command that operates on the given {@code TaskList} and {@code ParseInfo}.
     *
     * @throws CommandError if there is a validation error with the {@code ParseInfo}
     */
    public abstract String[] execute() throws CommandError;
}
