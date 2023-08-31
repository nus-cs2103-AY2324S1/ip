package duke.command;


import duke.exception.DukeException;
import duke.task.Deadline;
import duke.ui.Ui;
import duke.list.FunnyList;
import duke.storage.Storage;

/**
 * Represents a command to add a deadline task in the Duke application.
 * Extends the base Command class.
 */
public class AddDeadlineCommand extends Command {
	public static final String COMMAND_WORD = "deadline";

	private String taskDescription;
	private String by;

	/**
	 * Constructs an AddDeadlineCommand with the given task description and due date.
	 *
	 * @param taskDescription The description of the deadline task.
	 * @param by The due date of the deadline task.
	 */
	public AddDeadlineCommand(String taskDescription, String by) {
		this.taskDescription = taskDescription;
		this.by = by;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void execute(FunnyList taskList, Ui ui, Storage storage) throws DukeException  {
		Deadline task = new Deadline(this.taskDescription, false, this.by);
		taskList.add(task);
		storage.write(taskList);
		ui.showAddTodoMessage(task, taskList);
	}

}
