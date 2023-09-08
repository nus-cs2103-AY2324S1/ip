package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * The Duke class represents a simple chatbot application that helps manage tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath;

    /**
     * Constructs a new Duke chatbot instance with a specified file path for data storage.
     *
     * @param filePath The file path to load and save task data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        this.filePath = filePath;
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            List<Task> newTaskList = new ArrayList<>();
            tasks = new TaskList(newTaskList);
        }
    }

    /**
     * Runs the Duke chatbot.
     */
    public void run() {
        ui.showWelcomeMessage();
        String command = ui.readCommand();
        while (!Parser.isExitCommand(command)) {
            try {
                Parser.parseCommand(command, filePath, tasks);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
            command = ui.readCommand();
        }
        ui.showByeMessage();
        ui.closeScanner();
    }

    /**
     * The main method to start the Duke chatbot.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
