package jo;

import jo.command.Command;

/**
 * Represents a simple task management application.
 * It allows users to interact with tasks through a command-line interface.
 */
public class Jo {

    protected static String DEFAULT_FILE_PATH = "data/jo.txt";

    private Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private boolean isExit = false;

    /**
     * Constructs a new Jo object with the specified file path.
     *
     * @param filePath The file path for storing task data.
     */
    public Jo(String filePath) throws JoException {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (JoException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public Jo() throws JoException {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Runs the Jo application, displaying the welcome message and processing user commands.
     */
    public void run() {
        ui.showWelcome();
        while (!this.isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                this.isExit = c.isExitCommand();
            } catch (JoException e) {
                ui.showError(e.getMessage());
            }

        }
    }

    /**
     * Generates a String response to user input.
     *
     * @param input User input
     * @return String response to the user's input
     */
    String getResponse(String input) throws JoException {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            if (c.isExitCommand()) {
                this.isExit = true;
            }
        } catch (JoException e) {
            ui.showError(e.getMessage());
        }

        return ui.flushBuffer();
    }

    public static void main(String[] args) throws JoException {
        new Jo(DEFAULT_FILE_PATH).run();
    }
}
