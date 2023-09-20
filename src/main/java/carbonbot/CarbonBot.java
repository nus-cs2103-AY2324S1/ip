package carbonbot;

import carbonbot.command.Command;
import carbonbot.control.MainWindow;
import carbonbot.exception.CarbonDataFileException;
import carbonbot.exception.CarbonException;
import carbonbot.exception.CarbonStorageException;

/**
 * CarbonBot is a simple chatbot that helps to keep track of various things such as tasks.
 */

public class CarbonBot {
    private static final String DEFAULT_DATA_FILE_PATH = "./data/tasks.txt";
    private final Ui ui;
    private final Storage storage;
    private boolean shouldExit = false;
    private TaskList tasks;

    /**
     * Constructs a CarbonBot object that will read and write its data to the default file path.
     */
    public CarbonBot(MainWindow mainWindow) {
        this(DEFAULT_DATA_FILE_PATH, mainWindow);
    }

    /**
     * Constructs a CarbonBot object that will read and write its data to the specified file path.
     *
     * @param filePath Path to save file
     */
    public CarbonBot(String filePath, MainWindow mainWindow) {
        this.ui = new Ui(mainWindow);
        this.storage = new Storage(filePath);

        try {
            this.tasks = storage.getTasks();
        } catch (CarbonStorageException | CarbonDataFileException ex) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }


        this.ui.showGreetings();
    }

    /**
     * Returns whether the bot is ready to be terminated.
     * @return Boolean indicating if the bot received an ExitCommand
     */
    public boolean shouldExit() {
        return this.shouldExit;
    }

    /**
     * Processes a user input string to generate a response.
     * @param input The user input string to be processed.
     * @return A string representing the outcome of processing the input command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            if (c.isExit()) {
                this.shouldExit = true;
            }
        } catch (CarbonException e) {
            ui.bufferMessage(e.getMessage());
        }

        return ui.flushBuffer();
    }
}
