package joe.commands;

import joe.Storage;
import joe.TaskList;
import joe.Ui;
import joe.exceptions.JoeIndexOutOfBoundsException;

public class MarkCommand extends Command {
    private final int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoeIndexOutOfBoundsException {
        if (idx < 0 || idx > tasks.size()) {
            throw new JoeIndexOutOfBoundsException(idx);
        }

        tasks.get(idx - 1).markAsDone();
        ui.print(String.format("Nice! I've marked this task as done:%n %s", tasks.get(idx - 1)));

        storage.saveToFile(tasks);
    }
}
