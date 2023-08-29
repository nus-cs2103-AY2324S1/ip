package duke;

public class Duke {
	/**
	 * The storage object
	 */
	private final Storage storage;

	/**
	 * The task list object
	 */
	private final TaskList taskList;

	/**
	 * The ui object
	 */
	private final Ui ui;

	/**
	 * Constructor for Duke
	 *
	 * @param filePath the path to the file
	 */
	public Duke(String filePath) {
		this.storage = new Storage(filePath);
		this.ui = new Ui();
		this.taskList = this.storage.readFromFile();
	}

	/**
	 * The main function where the program starts
	 *
	 * @param args input args
	 */
	public static void main(String[] args) {
		new Duke("data/duke.txt").run();
	}

	/**
	 * The main function where the program starts
	 */
	public void run() {
		Parser parser = new Parser(this.taskList, this.storage, this.ui);
		ui.greet();
		parser.queryBot();
	}
}
