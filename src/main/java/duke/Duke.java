package duke;

/**
 * The chatbot object. Contains the logic of the chatbot.
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
     * Runs the chatbot with the given String input.
     */
    public boolean run(String text) {
        boolean isExit = false;
        try {
            if (text.isEmpty()) {
                throw new DukeException("Sorry, empty command is not supported");
            }
            Command c = Parser.parse(text);
            isExit = c.execute(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return isExit;

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse() {
        assert this.ui != null : "ui should have been initialised but it is not";
        return this.ui.respond();
    }
}
