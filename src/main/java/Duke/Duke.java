package Duke;

import Duke.Exceptions.DukeException;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.UI.Ui;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Contains the Duke Chatbot.
 *
 * @author Marcus Soh
 */
public class Duke {
    private static Path SAVE_FILE_LOCATION = Paths.get("data", "duke.txt");
  
    private Storage storage;
    private Ui ui;
    private TaskList listContainer = new TaskList(new ArrayList<>());

    /**
     * Constructor for our chatbot.
     *
     * @param filePath Specifies where the save file to store previous information is to be saved.
     */
    public Duke(String filePath) {

        this.storage = new Storage(filePath);

        try {
            this.listContainer = new TaskList(storage.load());
            ui = new Ui(this.listContainer, this.storage);
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

