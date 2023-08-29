public class UnidentifiedCommand extends Command {
	public UnidentifiedCommand(Printer out, TaskList taskList, FileIO savefile) {
		super(out, taskList, savefile);
	}

	@Override
	public void action() {
		throw new UnidentifiedCommandException();
	}
}
