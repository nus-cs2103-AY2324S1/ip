package duke.command;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;


public class ListCommand extends Command {

	@Override
	public void execute(TaskList taskList, Ui u, Storage s) {
		taskList.list();
	}
}
