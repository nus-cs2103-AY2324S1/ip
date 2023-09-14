package commands;

import components.DukeException;
import components.Storage;
import components.Ui;
import tasks.TaskList;

public class MarkAsDoneCommand extends Command {
    private Integer index;
    public MarkAsDoneCommand(Integer index) {
        super();
        this.index = index;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        try {
            return list.markAsDone(index, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
