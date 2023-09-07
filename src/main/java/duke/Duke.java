package duke;

import java.io.File;

import javafx.application.Platform;

/**
 * This class encapsulates the main program that will run.
 */
public class Duke {

    private static final String DEFAULT_FILE_PATH = "data/tasks.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();

    /**
     * Constructor for Duke
     *
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
    }

    public Duke() {
        this(DEFAULT_FILE_PATH);
    }

    public String loadSaveFile() {
        StringBuilder stringBuilder = new StringBuilder();

        File f = new File("./data");
        if (!f.exists() || !f.isDirectory()) {
            stringBuilder.append(Ui.directoryNotFound()).append("\n");
        }

        f = new File(DEFAULT_FILE_PATH);
        if (f.isFile()) {
            stringBuilder.append(Ui.savedFileFound());
        } else {
            stringBuilder.append(Ui.savedFileNotFound());
        }

        try {
            tasks = new TaskList(storage.loadFile());
            stringBuilder.append(Ui.showTaskList(tasks.taskArray, false));
        } catch (DukeException e) {
            stringBuilder.append(Ui.showExceptionError(e));
            tasks = new TaskList();
        }
        return stringBuilder.toString();
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(input, this.tasks, this.storage);
        String response = parser.parse();

        if (parser.isBye()) {
            return stopProgram(response);
        }
        return response;
    }

    public String stopProgram(String out) {
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                Platform.exit();
            } catch (IllegalArgumentException | InterruptedException e) {
                throw new DukeException(e.getMessage());
            }
        }).start();
        return out;
    }

    public Ui getUi() {
        return this.ui;
    }
}
