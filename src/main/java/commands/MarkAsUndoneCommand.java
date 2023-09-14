package commands;

import components.DukeException;
import components.Storage;
import components.Ui;
import tasks.TaskList;

public class MarkAsUndoneCommand extends Command {
    private Integer index;
    public MarkAsUndoneCommand(Integer index) {
        super();
        this.index = index;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return list.markAsUndone(index, storage);
    }
}
