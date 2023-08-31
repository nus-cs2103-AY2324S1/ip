package duke.command;

import duke.exception.ArgumentMustBeNumException;
import duke.io.FileIO;
import duke.io.Printer;
import duke.task.Task;
import duke.task.TaskList;

public class MarkCommand extends Command {
	private String s;

	public MarkCommand(Printer out, TaskList taskList, FileIO savefile, String s) {
		super(out, taskList, savefile);
		this.s = s;
	}

	@Override
	public void action() {
		Task task;
		try {
			task = taskList.getTask(Integer.parseInt(s));
		} catch (NumberFormatException e) {
			throw new ArgumentMustBeNumException(MARK);
		}

		task.mark();

		out.print("Nice! I've marked this task as done:", task.toString());
		save();
	}
}
