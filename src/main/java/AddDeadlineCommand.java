public class AddDeadlineCommand implements Command {
    private String deadlineName;
    private String deadlineString;
    public AddDeadlineCommand(String deadlineName, String deadlineString) {
        this.deadlineName = deadlineName;
        this.deadlineString = deadlineString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deadline  = Storage.parseDeadlineFromString(deadlineName, deadlineString);
        tasks.addTask(deadline);
        ui.printTaskAddedMessage(deadline, tasks);

    }
}
