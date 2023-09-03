package duke.command;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
public class ByeCommand extends Command {
	@Override
	public void execute(TaskList t, Ui u, Storage s) {
		String echo = "____________________________________________________________\n" +
				"Bye. Hope to see you again soon!\n" +
				"____________________________________________________________";
		System.out.println(echo);
		// write to file before leaving the system
		System.exit(0);
	}
}
