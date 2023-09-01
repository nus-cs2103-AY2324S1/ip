
public class DeadlineCommand extends Command{
    private String task;
    private String deadDate;
    public DeadlineCommand(String task, String deadDate) {
        this.task = task;
        this.deadDate = deadDate;
    }
    public void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException {
        Deadline task = new Deadline(this.task, this.deadDate);
        tasklist.add(task);
        fileStorage.write(tasklist);
        ui.showTaskAdded(task, tasklist);
    }
}
