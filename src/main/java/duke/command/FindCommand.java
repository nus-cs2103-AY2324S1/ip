package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Finds Task in taskList according to keyword.
 */
public class FindCommand extends Command {
	private String keyWord;

	public FindCommand(String keyWord) {
		this.keyWord = keyWord;
	}
	/**
	 * Finds the Tasks that contains keywords and prints thenm in a list
	 * @param t list of Task.
	 * @param u Ui to interact with User.
	 * @param s Storage to store Task.
	 */
	@Override
	public void execute(TaskList t, Ui u, Storage s) {
		t.findTask(this.keyWord);
	}
}
