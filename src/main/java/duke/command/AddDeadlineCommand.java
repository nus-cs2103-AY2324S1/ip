package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.ui.Ui;
import duke.list.FunnyList;
import duke.storage.Storage;

public class AddDeadlineCommand extends Command {
	public static final String COMMAND_WORD = "deadline";

	private String taskDescription;
	private String by;

	public AddDeadlineCommand(String taskDescription, String by) {
		this.taskDescription = taskDescription;
		this.by = by;
	}

	@Override
	public void execute(FunnyList taskList, Ui ui, Storage storage) throws DukeException  {
		Deadline task = new Deadline(this.taskDescription, false, this.by);
		taskList.add(task);
		storage.write(taskList);
		ui.showAddTodoMessage(task, taskList);
	}

}
