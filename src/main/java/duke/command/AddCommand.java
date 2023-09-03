package duke.command;
import java.io.IOException;
import duke.task.TaskList;
import duke.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;


public class AddCommand extends Command {

	private final Task task;
	public AddCommand(Task task) {
		this.task = task;
	}

	@Override
	public void execute(TaskList taskList, Ui u, Storage storage) {
		taskList.addTask(task);
		try {
			storage.writeFile(task);
		} catch (IOException e) {
			System.out.println("Fail to write to file\n" + e.getMessage());
		}
	}

}

