package duke;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.scene.image.Image;


/**
 * Contains the Duke Chatbot.
 *
 * @author Marcus Soh
 */
public class Duke {
    public static final Path SAVE_FILE_LOCATION = Paths.get("data", "duke.txt");
    private Ui ui;
    private TaskList listContainer = new TaskList(new ArrayList<>());
    private Storage storage = new Storage(String.valueOf(SAVE_FILE_LOCATION));

    private Image userAvatar = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeAvatar = new Image(this.getClass().getResourceAsStream("/images/elonmusk.jpg"));


    /**
     * Constructor for our chatbot.
     *
     * @param filePath Specifies where the save file to store previous information is to be saved.
     */
    public Duke() {



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
        new Duke().run();
    }



    public String getResponse(String input) {
        // TODO
        try {
            Parser.parseInput(input, listContainer, storage);
            return "";
        } catch (DukeException e) {
            return e.getMessage();
        }

    }
}

