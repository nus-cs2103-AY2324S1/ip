public class UndoTask extends Command {
    private int taskToUndo;

    public UndoTask(int taskToUndo) {
        this.taskToUndo = taskToUndo;
    }
    @Override
    public void execute(TaskList lst, Ui ui, Storage storage) {
        lst.get(taskToUndo).setUncompleted();
        ui.uncompletedMessage(taskToUndo, lst);
        try {
            storage.saveTasks(lst);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
