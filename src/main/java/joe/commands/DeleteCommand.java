package joe.commands;

import joe.Storage;
import joe.TaskList;
import joe.Ui;
import joe.exceptions.JoeIndexOutOfBoundsException;
import joe.tasks.Task;

public class DeleteCommand extends Command {
    private final int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoeIndexOutOfBoundsException {
        if (idx < 0 || idx > tasks.size()) {
            throw new JoeIndexOutOfBoundsException(idx);
        }

        Task deletedTask = tasks.get(idx - 1);
        tasks.remove(idx - 1);
        
        ui.print(
                String.format(
                        "Noted. I've removed this task:%n %s%nNow you have %d tasks in the list.",
                        deletedTask, tasks.size()));

        storage.saveToFile(tasks);
    }
}
