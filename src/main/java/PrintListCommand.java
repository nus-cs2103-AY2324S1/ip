public class PrintListCommand extends Command {

    public PrintListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
