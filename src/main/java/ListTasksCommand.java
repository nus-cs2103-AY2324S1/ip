public class ListTasksCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            ui.printTask(i, task);
        }
    }
}
