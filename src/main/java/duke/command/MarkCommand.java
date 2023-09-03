package duke.command;
import duke.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
public class MarkCommand extends Command {
	private final boolean toMark;
	private final int pos;
	public MarkCommand(boolean toMark, int pos) {
		this.toMark = toMark;
		this.pos = pos;
	}

	@Override
	public void execute(TaskList taskList, Ui u, Storage s) {
		try {
			taskList.toMark(toMark, pos);
		} catch (DukeException e) {
			System.out.println(e.getMessage());
		}
	}
}
