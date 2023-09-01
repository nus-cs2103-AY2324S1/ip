package kiera.command;

import kiera.Storage;
import kiera.task.Task;
import kiera.TaskList;
import kiera.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String desc) {
        setDescription(desc);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int index = Integer.parseInt(this.getDescription());
        Task task = tasks.getTaskByIndex(index);
        tasks.remove(task);
        storage.save(tasks);
        ui.showDeleteNotice(task, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
