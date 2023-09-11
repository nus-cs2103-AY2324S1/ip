package duke;

/**
 * UndoTask class that contains the index for task to undo
 */
public class UndoTask extends Command {
    private int taskToUndo;
    private String notUnmarkedProperly = "Task not marked undone properly";

    /**
     * Constructor for UndoTask command
     * @param taskToUndo the integer in the TaskList which we want to undo
     */
    public UndoTask(int taskToUndo) {
        this.taskToUndo = taskToUndo;
    }
    @Override
    public String execute(TaskList lst, Ui ui, Storage storage) {
        lst.get(taskToUndo).setUncompleted();
        assert lst.get(taskToUndo).getCheckbox().equals("[ ] ") : this.notUnmarkedProperly;
        try {
            storage.saveTasks(lst);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
        return ui.uncompletedMessage(taskToUndo, lst);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
