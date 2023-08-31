package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.ui.Ui;
import duke.list.FunnyList;
import duke.storage.Storage;

/**
 * Represents a command to add an event task in the Duke application.
 * Extends the base Command class.
 */
public class AddEventCommand extends Command {
	public static final String COMMAND_WORD = "event";

	private String taskDescription;
	private String from;
	private String to;

	/**
	 * Constructs an AddEventCommand with the given task description, start time, and end time.
	 *
	 * @param taskDescription The description of the event task.
	 * @param from The start time of the event.
	 * @param to The end time of the event.
	 */
	public AddEventCommand(String taskDescription, String from, String to) {
		this.taskDescription = taskDescription;
		this.from = from;
		this.to = to;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void execute(FunnyList taskList, Ui ui, Storage storage) throws DukeException  {
		Event task = new Event(this.taskDescription, false, this.from, this.to);
		taskList.add(task);
		storage.write(taskList);
		ui.showAddTodoMessage(task, taskList);
	}

}