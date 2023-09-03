public class PrintListCommand extends Command {
    public PrintListCommand() {
        super();
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        System.out.println(taskList);
    }
}
