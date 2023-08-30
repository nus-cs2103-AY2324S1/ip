package cyrus.commands;

import cyrus.parser.ParseInfo;
import cyrus.tasks.TaskList;
import cyrus.ui.Ui;

public class MarkTaskCommand extends Command {
    public MarkTaskCommand(TaskList taskList, ParseInfo parseInfo) {
        super(taskList, parseInfo);
    }

    @Override
    public void execute() throws CommandError {
        if (this.parseInfo.hasNoArgument()) {
            throw new CommandError("Missing task index");
        }

        try {
            int i = Integer.parseInt(this.parseInfo.getArgument());
            this.taskList.setTaskStatus(i - 1, true);
            Ui.printText("Nice! I've marked this task as done:",
                    this.taskList.getTask(i - 1).toString());
        } catch (NumberFormatException e) {
            throw new CommandError("Invalid task index: must be integer");
        } catch (IndexOutOfBoundsException e) {
            throw new CommandError("Invalid task index: index out of bounds");
        }
    }
}
