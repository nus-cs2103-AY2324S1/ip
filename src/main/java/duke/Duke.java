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

        doDirectory(stringBuilder);
        doFile(stringBuilder);
        initialiseTask(stringBuilder);

        return stringBuilder.toString();
    }
    public String getResponse(String input) {
        Parser parser = new Parser(input, tasks, storage);
        String response = parser.parse();

        assert !response.isEmpty();

        if (parser.isBye()) {
            return stopProgram(response);
        }
        return response;
    }

    public String stopProgram(String response) {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                Platform.exit();
            } catch (IllegalArgumentException | InterruptedException e) {
                throw new DukeException(e.getMessage());
            }
        }).start();
        return response;
    }

    public Ui getUi() {
        return ui;
    }

    private void doDirectory(StringBuilder stringBuilder) {
        File f = new File("./data");
        if (!f.exists() || !f.isDirectory()) {
            stringBuilder.append(Ui.directoryNotFound()).append("\n");
        }
    }

    private void doFile(StringBuilder stringBuilder) {
        if (new File(DEFAULT_FILE_PATH).isFile()) {
            stringBuilder.append(Ui.savedFileFound());
        } else {
            stringBuilder.append(Ui.savedFileNotFound());
        }
        stringBuilder.append("\n");
    }

    private void initialiseTask(StringBuilder stringBuilder) {
        try {
            tasks = new TaskList(storage.loadFile());
            stringBuilder.append(Ui.showTaskList(tasks.taskArray, false)).append("\n");
        } catch (DukeException e) {
            stringBuilder.append(Ui.showExceptionError(e));
            tasks = new TaskList();
        }
        tasks.doReminder(stringBuilder);
    }
}
