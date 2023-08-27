package robert;

import robert.command.Command;
import robert.exception.RobertException;
import robert.parser.Parser;
import robert.storage.Storage;
import robert.task.TaskList;
import robert.ui.Ui;

/**
 * This class simulates a robot application named Robert. It is designed to receive user commands
 * via CLI for task tracking. Some commands include adding and deleting tasks, marking and
 * unmarking tasks, as well as listing and clearing tasks that were stored.
 *
 * @author Lee Zhan Peng
 */
public class Robert {

	/** Ui for CLI printing of actions and exceptions */
	private final Ui ui;

	/** Storage to upload and download stored tasks in hard disk */
	private final Storage storage;

	/** List of tasks stored */
	private TaskList tasks;

	/**
	 * Constructs Robert.
	 *
	 * @param filePath path from root folder to data folder which
	 *        stores a txt file that contains previous saved tasks.
	 *        If path does not lead to the txt file/directory, new
	 *        file/directory will be created.
	 */
	public Robert(String filePath) {
		this.ui = new Ui();
		this.storage = new Storage(filePath);

		try {
			this.tasks = new TaskList(this.storage.load());
		} catch (RobertException e) {
			this.ui.showLoadingError();
			this.tasks = new TaskList();
		}
	}

	/**
	 * Begin running Robert in the CLI.
	 */
	private void run() {
		this.ui.showWelcome();
		boolean isExit = false;
		while (!isExit) {
			try {
				String fullCommand = this.ui.readCommand();
				this.ui.showLine(); // show the divider line ("_______")
				Command c = Parser.parse(fullCommand);
				c.execute(this.tasks, this.ui, this.storage);
				this.storage.save(this.tasks);
				isExit = c.isExit();
			} catch (RobertException e) {
				this.ui.showError(e.getMessage());
			} finally {
				this.ui.showLine();
			}
		}
	}

	/**
	 * The application's entry point.
	 *
	 * @param args an array of command-line arguments for the application.
	 */
	public static void main(String[] args) {
		new Robert("data/tasks.txt").run();
	}
}
