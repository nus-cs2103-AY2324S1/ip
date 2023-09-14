package command;

import exception.DialogixException;
import main.Storage;
import main.TaskList;
import main.Ui;

public class DoneCommand extends Command {
    private int zeroBasedIndex;

    public DoneCommand(int oneBasedIndex) {
        this.zeroBasedIndex = oneBasedIndex - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DialogixException {
        if (zeroBasedIndex < 0 || zeroBasedIndex >= tasks.size()) {
            throw new DialogixException("OOPS!!! The index to mark as done cannot be less than 0 or "
                    + "greater than the length of the list.");
        }
        tasks.addToStack();
        tasks.markTaskAsDone(zeroBasedIndex);
        ui.printMessage("Nice! I've marked this task as done:\n\t\t" + tasks.get(zeroBasedIndex));
        storage.save(tasks.getAllTasks());
    }
}

