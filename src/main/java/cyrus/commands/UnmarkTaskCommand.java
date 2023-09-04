package cyrus.commands;

import cyrus.parser.ParseInfo;
import cyrus.tasks.TaskList;

/**
 * Command to unmark a {@code Task} in the given {@code TaskList}.
 */
public class UnmarkTaskCommand extends Command {
    public UnmarkTaskCommand(TaskList taskList, ParseInfo parseInfo) {
        super(taskList, parseInfo);
    }

    /**
     * Marks the {@code Task} corresponding to the command's argument as not done.
     *
     * @throws CommandError if no argument is present or if the value given is out of bounds or not
     *                      a number.
     */
    @Override
    public String[] execute() throws CommandError {
        if (this.parseInfo.hasNoArgument()) {
            throw new CommandError("Missing task index");
        }

        try {
            int i = Integer.parseInt(this.parseInfo.getArgument());
            this.taskList.setTaskStatus(i - 1, false);
            return new String[]{
                "OK, I've marked this task as not done yet:",
                this.taskList.getTask(i - 1).toString()
            };
        } catch (NumberFormatException e) {
            throw new CommandError("Invalid task index: must be integer");
        } catch (IndexOutOfBoundsException e) {
            throw new CommandError("Invalid task index: index out of bounds");
        }
    }
}
