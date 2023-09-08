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
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            list.markAsDone(index, storage);
        } catch (DukeException e) {
            System.out.println(Ui.LINE);
            System.out.println(e);
            System.out.println(Ui.LINE);
        }
    }
}
