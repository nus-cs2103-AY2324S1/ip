package duke.commands;

import duke.storage.Storage;
import duke.tasks.*;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class AddCommand extends Command {
	/**
	 * The description of the task
	 */
	private final String description;
	/**
	 * The type of the task, either mark or unmark
	 */
	private final String type;
	/**
	 * The end time of the task
	 */
	private LocalDateTime to;
	/**
	 * The start time of the task
	 */
	private LocalDateTime from;

	/**
	 * duke.tasks.Todo Constructor
	 *
	 * @param description
	 */
	public AddCommand(String description) {
		this.description = description;
		this.type = "todo";
	}

	/**
	 * duke.tasks.Deadline Constructor
	 *
	 * @param description the description of the task
	 * @param to          the end time of the task
	 */
	public AddCommand(String description, LocalDateTime to) {
		this.description = description;
		this.to = to;
		this.type = "deadline";
	}

	/**
	 * duke.tasks.Event Constructor
	 *
	 * @param description the description of the task
	 * @param from        the start time of the task
	 * @param to          the end time of the task
	 */
	public AddCommand(String description, LocalDateTime from, LocalDateTime to) {
		this.description = description;
		this.to = to;
		this.from = from;
		this.type = "event";
	}

	/**
	 * Executes the command
	 *
	 * @param tasks   the task list
	 * @param ui      the ui
	 * @param storage the storage
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		Task task = null;
		switch (this.type) {
			case "todo":
				task = new Todo(this.description);
				break;
			case "deadline":
				task = new Deadline(this.description, this.to);
				break;
			case "event":
				task = new Event(this.description, this.from, this.to);
				break;
		}
		tasks.add(task);
		ui.showAdd(tasks.size(), task);
		storage.writeData(tasks.getAllTasks());
	}

	/**
	 * Returns whether the program should exit
	 *
	 * @return whether the program should exit
	 */
	@Override
	public boolean isExit() {
		return false;
	}


}
