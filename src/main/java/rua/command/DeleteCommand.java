package rua.command;

import rua.command.Command;
import rua.task.TaskList;
import rua.common.*;

public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.showMessage(" Noted. I've removed this task:\n");
        ui.showMessage("    " + tasks.getTasks().get(index - 1) + "\n");
        TaskList newTasks = tasks.delete(index);
        ui.showMessage("Now you have " + tasks.getTasks().size() +
                " tasks in the list.\n");
        return newTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand c = (DeleteCommand) o;
        return c.index == this.index;
    }
}