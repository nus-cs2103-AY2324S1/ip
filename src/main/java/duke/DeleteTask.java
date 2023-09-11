package duke;

/**
 * Delete Task class with int index that represents which task in TaskList to delete
 *
 * @author wj331
 */
public class DeleteTask extends Command {
    private int indexToDelete;
    private String notDeletedProperly = "Task not deleted properly";

    /**
     * Constructor for DeleteTask
     * @param indexToDelete the index of task we want to delete from TaskList
     */
    public DeleteTask(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        if (indexToDelete >= tasks.size()) {
            throw new InvalidInputException("OOPS!!! Too few tasks");
        }
        int listSizeBef = tasks.size();
        Task tsk = tasks.get(this.indexToDelete);
        tasks.remove(this.indexToDelete);
        int listSizeAft = tasks.size();
        //Ensures a change in size of list
        assert listSizeBef == listSizeAft + 1 : this.notDeletedProperly;
        try {
            storage.saveTasks(tasks);
        } catch (InvalidInputException e) {
            ui.printException(e.getMessage());
        }
        return ui.deletedMessage(tsk);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
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
