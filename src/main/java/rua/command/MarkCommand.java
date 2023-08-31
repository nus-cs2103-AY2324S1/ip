package rua.command;

import rua.command.Command;
import rua.task.TaskList;
import rua.common.*;

public class MarkCommand implements Command {
    private final Boolean marked;
    private final int index;

    public MarkCommand(int index, Boolean marked) {
        this.index = index;
        this.marked = marked;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (marked) {
            ui.showMessage("Nice! I've marked this task as done:\n");
        } else {
            ui.showMessage("OK, I've marked this task as not done yet:\n");
        }
        TaskList newTasks = marked ? tasks.mark(index) : tasks.unmark(index);
        ui.showMessage("    " + tasks.getTasks().get(index - 1));
        return newTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof MarkCommand)) {
            return false;
        }

        MarkCommand c = (MarkCommand) o;
        return c.marked.equals(this.marked) && c.index == this.index;
    }
}
