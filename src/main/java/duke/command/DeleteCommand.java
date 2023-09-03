package duke.command;
import java.io.IOException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
public class DeleteCommand extends Command {
	private final int pos;
	public DeleteCommand(int pos) {
		this.pos = pos;
	}
	@Override
	public void execute(TaskList taskList, Ui u, Storage s) {
		try {
			taskList.delete(this.pos);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
	}
}
