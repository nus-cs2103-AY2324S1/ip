package duke.command;

import duke.tasklist.TaskList;

/**
 * The Command class represents a command provided by the user for the ChatterChicken task manager application.
 * It encapsulates both the action and task description specified by the user.
 */
public abstract class Command {
    public static String INDENT = "      ";

    public abstract String execute(TaskList tasks);
}
