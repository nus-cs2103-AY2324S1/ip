package duke;

import duke.task.TaskList;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents an intelligent chat robot that helps a person to keep track of various things with encouraging quotes.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Initializes the chat robot, loads past data from the specified file path.
     *
     * @param filePath the file path to load past data from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chat robot to start the interaction. Save data to file after user exits.
     */
    public void run() {
        ui.run(new Parser(tasks));
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showSavingError();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}

