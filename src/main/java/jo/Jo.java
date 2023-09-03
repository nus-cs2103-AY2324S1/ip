package jo;

import jo.command.Command;

/**
 * Represents a simple task management application.
 * It allows users to interact with tasks through a command-line interface.
 */
public class Jo {
    protected static String filePath = "data/jo.txt";

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new Jo object with the specified file path.
     *
     * @param filePath The file path for storing task data.
     */
    public Jo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JoException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Jo application, displaying the welcome message and processing user commands.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JoException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Jo(filePath).run();
    }
}
