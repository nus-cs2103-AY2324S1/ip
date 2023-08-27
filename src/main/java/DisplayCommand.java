public class DisplayCommand extends Command {
    public DisplayCommand() {
        super.isExit = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.stringFormat(tasks.displayList());
    }
}
