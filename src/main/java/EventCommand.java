public class EventCommand extends Command{
    private String description;
    private String from;
    private String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event event = new Event(description, from, to);
        taskList.addTask(event);
        storage.saveTask(taskList.getTasks());
        ui.showTaskAdded(event, taskList.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
