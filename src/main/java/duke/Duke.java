package duke;


import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class Duke {
	/**
	 * The task list object
	 */
	private TaskList taskList;
	/**
	 * The ui object
	 */
	private Ui ui;
	/**
	 * The storage object
	 */
	private Storage storage;

	/**
	 * Constructor for Duke
	 *
	 * @param filePath the path to the file
	 */
	public Duke(String filePath) {
		this.storage = new Storage(filePath);
		this.taskList = new TaskList(this.storage.readData());
		this.ui = new Ui();
	}

	/**
	 * The main function where the program starts
	 *
	 * @param args input args
	 */
	public static void main(String[] args) {
		new Duke("data/duke.txt").run();
	}

	public void run() {
		this.ui.showWelcome();
		boolean isExit = false;
		while (!isExit) {
			try {
				String command = ui.readCommand();
				Command c = Parser.parse(command);
				c.execute(this.taskList, this.ui, this.storage);
				isExit = c.isExit();
			} catch (DukeException e) {
				this.ui.printLine();
				System.out.println(e.getMessage());
				this.ui.printLine();
			}
		}
	}


}
