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
	 * @param taskList list of Task.
	 * @param ui Ui to interact with User.
	 * @param storage Storage to store Task.
	 */
	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		StringBuilder br = taskList.findTask(this.keyWord);
		System.out.println(ui.showFindTask(br));
		return ui.showFindTask(br);
	}
}
