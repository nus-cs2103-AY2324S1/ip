public class ByeCommand extends Command{
    public ByeCommand () {
        this.isExit = true;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(String.format("Bye. Hope to see you again soon!"));
        ui.exit();
        storage.saveToFile(tasks);
    }
}
