package duke;

import duke.commands.Command;

import duke.components.Parser;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;

import duke.exceptions.DukeException;

/**
 * Chatbot implementation
 */
public class Duke {
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;


    /**
     * Constructs a Duke object
     * @param fileDir Relative path to save task list
     * @param fileName Name of file to save task list
     */
    public Duke(String fileDir, String fileName) {
        storage = new Storage(fileDir, fileName);
        taskList = new TaskList(storage.load());
        ui = new Ui();
        parser = new Parser();
    }

    /**
     * Constructs a new Duke instance and runs it
     * @param args Command-line arguments passed in at startup
     */
    public static void main(String[] args) {
        new Duke("./data/", "duke.txt").run();
    }

    /**
     * Runs the chatbot, listens to user inputs, and executes commands
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }
}
