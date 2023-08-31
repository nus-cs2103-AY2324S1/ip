package duke.command;

import duke.io.FileIO;
import duke.io.Printer;
import duke.task.Task;
import duke.task.TaskList;
import java.util.Map;

public class AddTaskCommand extends Command {
	String type;
	String name;
	Map<String, String> arguments;

	public AddTaskCommand(
			Printer out,
			TaskList taskList,
			FileIO savefile,
			String type,
			String name,
			Map<String, String> arguments) {
		super(out, taskList, savefile);
		this.type = type;
		this.name = name;
		this.arguments = arguments;
	}

	@Override
	public void action() {
		Task task = Task.createTask(type, name, arguments);
		taskList.addTask(task);
		out.print("Got it. I've added this task:", task.toString(), taskList.getNumberOfTasks());
		save();
	}
}
