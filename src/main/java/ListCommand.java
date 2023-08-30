public class ListCommand extends Command {
    public ListCommand() {
        super(CommandType.LIST);
    }

    public void execute(TaskList tasks, Ui ui) {
        ui.listTasks(tasks);
    }
}
