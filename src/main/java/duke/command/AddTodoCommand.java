package duke.command;

import duke.exception.DukeException;
import duke.task.ToDo;
import duke.ui.Ui;
import duke.list.FunnyList;
import duke.storage.Storage;

public class AddTodoCommand extends Command {
	public static final String COMMAND_WORD = "todo";

	private String taskDescription;

	public AddTodoCommand(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	@Override
	public void execute(FunnyList taskList, Ui ui, Storage storage) throws DukeException  {
		ToDo task = new ToDo(this.taskDescription);
		taskList.add(task);
		storage.write(taskList);
		ui.showAddTodoMessage(task, taskList);
	}

}
