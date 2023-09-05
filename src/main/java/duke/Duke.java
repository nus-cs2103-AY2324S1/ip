package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.DukeList;
import duke.ui.Ui;
import javafx.application.Application;


/**
 * Represents the main application class for Duke.
 */
public class Duke {
    private DukeList itemList;
    private Storage storage;
    private Ui ui;

    /**
     * initialises a duke chat bot object
     */
    public Duke() {
        ui = new Ui();
        ui = new Ui();
        this.storage = new Storage("data/duke.txt");

        try {
            this.itemList = new DukeList(this.storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.itemList = new DukeList();
        }
    }

    /**
     * Constructs a Duke instance with the given file path.
     *
     * @param filePath The path to the data file.
     */
    public Duke(String filePath) {

    }

    /**
     * The main method that initializes and runs the Duke application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }


    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(itemList, ui, storage);
        } catch (DukeException err) {
            return err.getMessage();
        }
    }
}



