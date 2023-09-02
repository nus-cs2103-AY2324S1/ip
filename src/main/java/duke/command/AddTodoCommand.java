package duke.command;

import duke.exception.DukeException;
import duke.task.ToDo;
import duke.ui.Ui;
import duke.list.FunnyList;
import duke.storage.Storage;

/**
 * Represents a command to add a todo task in the Duke application.
 * Extends the base Command class.
 */
public class AddTodoCommand extends Command {
	public static final String COMMAND_WORD = "todo";

	private String taskDescription;

	/**
	 * Constructs a AddTodoCommand with the given task description.
	 *
	 * @param taskDescription The description of the todo task.
	 */
	public AddTodoCommand(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void execute(FunnyList taskList, Ui ui, Storage storage) throws DukeException  {
		ToDo task = new ToDo(this.taskDescription);
		taskList.add(task);
		storage.write(taskList);
		ui.showAddTodoMessage(task, taskList);
	}

}
