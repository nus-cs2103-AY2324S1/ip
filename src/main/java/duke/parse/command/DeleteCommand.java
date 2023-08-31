package duke.parse.command;

import duke.Duke;

public class DeleteCommand implements Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean execute(Duke bot) {
        bot.deleteTask(this.taskIndex);
        return true;
    }

    @Override
    public boolean equals(Object another) {
        if (another instanceof DeleteCommand) {
            DeleteCommand anotherDelete = (DeleteCommand) another;
            return this.taskIndex == anotherDelete.taskIndex;
        }
        return false;
    }
}
