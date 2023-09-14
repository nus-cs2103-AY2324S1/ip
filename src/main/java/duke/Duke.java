package duke;

import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;

import command.Command;

/**
 * A chatbot. Able to read user input and perform a series of tasks.
 */
public class Duke {
    private static final String NAME = "DEREK";
    private static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HHmm";
    public static final DateTimeFormatter DATE_TIME_INPUT_FORMATTER =
            DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);

    private TaskList tasks;
    private Storage storage;
    private UI ui;

    /**
     * Constructor for Duke class.
     *
     * @param filePath Relative path of file where data is to be stored.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new UI(NAME);
        try {
            this.tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            ui.getLoadingErrorMessage();
            this.tasks = new TaskList();
        }
    }

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        this.storage = new Storage("./data/state.txt");
        this.ui = new UI(NAME);
        try {
            this.tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            ui.getLoadingErrorMessage();
            this.tasks = new TaskList();
        }
        assert this.storage != null : "storage of Duke should not be null";
        assert this.ui != null : "ui of Duke should not be null";
        assert this.tasks != null : "taskList of Duke should not be null";
    }

    String getResponse(String input) {
        try {
            Command command = Parser.parseUserInput(input);
            assert command != null : "Command returned from parsing user input should not be null";

            String response = command.execute(tasks, ui, storage);
            assert response != null : "Response returned from executing command should not be null";

            if (command.isExit()) {
                javafx.application.Platform.exit();
            }
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }
}
