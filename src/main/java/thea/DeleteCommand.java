package thea;

public class DeleteCommand extends Command {
     int index;
    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            try {
                ui.taskDelete(tasks.get(this.index), tasks);
            } catch (java.lang.IndexOutOfBoundsException e){
                throw new IndexOutOfBoundsException("There is currently no task " + (index + 1));
            }
            tasks.delete(this.index);
        } catch (IndexOutOfBoundsException e) {
            ui.showError(e.getMessage());
        }
        storage.saveTaskList(tasks);
    }
}
