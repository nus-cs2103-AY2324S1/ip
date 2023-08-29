public class Duke {
	private final Storage storage;

	private final TaskList taskList;

	private final Ui ui;

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

	public void run() {
		Parser parser = new Parser(this.taskList, this.storage, this.ui);
		ui.greet();
		parser.queryBot();
	}
}
