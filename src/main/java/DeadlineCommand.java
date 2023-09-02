public class DeadlineCommand extends Command {
    private Storage storage;
    private Deadline deadline;

    public DeadlineCommand(TaskList taskList, Ui ui, Storage storage, String[] descriptions) throws IllegalArgumentDukeException {
        super(taskList, ui);
        this.storage = storage;
        this.deadline = new Deadline(descriptions[0], Parser.parseDate(descriptions[1]));
    }
    @Override
    public void execute() {
        taskList.getTasks().add(deadline);
        storage.saveTask(deadline);
        ui.showTaskAdded(deadline, taskList.getTasks().size());
    }
}
