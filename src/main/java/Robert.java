public class Robert {

	private final Storage storage;
	private final Ui ui;
	private TaskList tasks;

	public Robert(String filePath) {
		this.storage = new Storage(filePath);
		this.ui = new Ui();

		try {
			this.tasks = new TaskList(this.storage.load());
		} catch (RobertException e) {
			this.ui.showLoadingError();
			this.tasks = new TaskList();
		}
	}

	public void run() {
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

	public static void main(String[] args) {
		new Robert("data/tasks.txt").run();
	}
}