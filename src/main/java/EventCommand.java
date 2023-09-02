public class EventCommand extends Command {
    private Storage storage;
    private Event event;


    public EventCommand(TaskList taskList, Ui ui, Storage storage, String[] descriptions) {
        super(taskList, ui);
        this.storage = storage;
        this.event = new Event(descriptions[0], descriptions[1], descriptions[2]);
    }
    @Override
    public void execute() {
        taskList.getTasks().add(event);
        storage.saveTask(event);
        ui.showTaskAdded(event, taskList.getTasks().size());
    }
}
