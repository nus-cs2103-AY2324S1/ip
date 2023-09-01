package duke;

/**
 * The chatbot object and also the entry point to start the chatbot.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Creates the Duke chatbot object.
     *
     * @param filePath The path to store the tasks.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Runs the chatbot with the main looping logic.
     */
    public void run() {
        this.ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                // ui reads in one line of input
                String text = ui.readCommand();
                if (text.isEmpty()) {
                    throw new DukeException("Sorry, empty command is not supported");
                }
                Command c = Parser.parse(text);
                isExit = c.execute(this.tasks, this.ui, this.storage);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.goodBye();
    }

    public static void main(String[] args) {
        Duke bot = new Duke("data/tasks.txt");
        bot.run();
    }
}
