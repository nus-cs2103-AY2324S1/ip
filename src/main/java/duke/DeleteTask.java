package duke;

import duke.Command;

public class DeleteTask extends Command {
    private int indexToDelete;

    public DeleteTask(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.remove(this.indexToDelete);
        ui.deletedMessage(this.indexToDelete, tasks);
        try {
            storage.saveTasks(tasks);
        } catch (InvalidInputException e) {
            ui.printException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (this == null || obj == null) {
            return false;
        } else {
            if (obj instanceof DeleteTask) {
                DeleteTask deletetask = (DeleteTask) obj;
                return this.indexToDelete == deletetask.indexToDelete;
            }
            return false;
        }
    }
}
