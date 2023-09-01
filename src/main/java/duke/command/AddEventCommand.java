package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.ui.Ui;
import duke.list.FunnyList;
import duke.storage.Storage;

public class AddEventCommand extends Command {
	public static final String COMMAND_WORD = "event";

	private String taskDescription;
	private String from;
	private String to;

	public AddEventCommand(String taskDescription, String from, String to) {
		this.taskDescription = taskDescription;
		this.from = from;
		this.to = to;
	}

	@Override
	public void execute(FunnyList taskList, Ui ui, Storage storage) throws DukeException  {
		Event task = new Event(this.taskDescription, false, this.from, this.to);
		taskList.add(task);
		storage.write(taskList);
		ui.showAddTodoMessage(task, taskList);
	}

}