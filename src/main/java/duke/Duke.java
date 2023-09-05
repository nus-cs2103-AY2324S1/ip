package duke;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;


/**
 * Contains the Duke Chatbot.
 *
 * @author Marcus Soh
 */
public class Duke {
    private static final Path SAVE_FILE_LOCATION = Paths.get("data", "duke.txt");
    private Ui ui;
    private TaskList listContainer = new TaskList(new ArrayList<>());

    /**
     * Constructor for our chatbot.
     *
     * @param filePath Specifies where the save file to store previous information is to be saved.
     */
    public Duke(String filePath) {

        Storage storage = new Storage(filePath);

        try {
            this.listContainer = new TaskList(storage.load());
            ui = new Ui(this.listContainer, storage);
        } catch (DukeException e) {
            System.out.println(e.getErrorMessage());
        }
    }

    /**
     * Begins the chatbot.
     */
    private void run() {
        ui.beginLogging();
    }

    public static void main(String[] args) {
        new Duke(String.valueOf(SAVE_FILE_LOCATION)).run();
    }
}

