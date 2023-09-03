package kiera.command;

import kiera.Storage;
import kiera.TaskList;
import kiera.Ui;
import kiera.exception.KieraException;
import kiera.task.Task;

/**
 * Represents a command for deleting a tasl from tha taskList.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String desc) {
        setDescription(desc);
    }

    /**
     * @inheritDoc
     *
     * @throws KieraException If there is an error with task type or storage operation.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KieraException {
        try {
            int index = Integer.parseInt(this.getDescription());
            Task task = tasks.getTaskByIndex(index);
            tasks.remove(task);
            storage.save(tasks);
            ui.showDeleteNotice(task, tasks.getSize());
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new KieraException(e.getMessage());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
