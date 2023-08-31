package duke.command;

import duke.exception.ArgumentMustBeNumException;
import duke.io.FileIO;
import duke.io.Printer;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
	private String s;

	public DeleteCommand(Printer out, TaskList taskList, FileIO savefile, String name) {
		super(out, taskList, savefile);
		this.s = name;
	}

	@Override
	public void action() {
		Task task;
		try {
			int i = Integer.parseInt(s); // 1-indexed
			task = taskList.getTask(i);
			taskList.deleteTask(i);
		} catch (NumberFormatException e) {
			throw new ArgumentMustBeNumException(DELETE);
		}

		out.print("Noted. I've removed this task:", task.toString(), taskList.getNumberOfTasks());
		save();
	}
}
