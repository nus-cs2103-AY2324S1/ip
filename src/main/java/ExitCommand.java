public class ExitCommand extends Command {

	public ExitCommand() {
		super(true);
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ui.close();
		ui.showMessage("Bye. Hope to see you again soon!");
	}
}
