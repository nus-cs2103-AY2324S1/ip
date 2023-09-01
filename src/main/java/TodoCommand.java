
public class TodoCommand extends Command{
    private String task;
    public TodoCommand(String task) {
        this.task = task;
    }
    public void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException {
        Todo task = new Todo(this.task);
        tasklist.add(task);
        fileStorage.write(tasklist);
        ui.showTaskAdded(task, tasklist);
    }
}
