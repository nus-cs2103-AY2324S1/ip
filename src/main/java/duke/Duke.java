package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents a chatbot that helps users to keep track of their tasks.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a Duke object.
     *
     * @param foldPath The path of the folder to store the data file.
     * @param fileName The name of the data file.
     */
    public Duke(String foldPath, String fileName) {
        this.storage = new Storage(foldPath, fileName);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            storage.createFile();
            taskList = new TaskList();
        }
    }

    /**
     * Runs the chatbot.
     */
    public void start() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
                System.out.println();
            }
        }
    }

    /**
     * Runs the chatbot.
     *
     * @param args The command line arguments passed in.
     */
    public static void main(String[] args) {
        Duke robot404 = new Duke("./data", "duke.txt");
        robot404.start();
    }
}
