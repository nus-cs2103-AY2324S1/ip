package duke;


import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.DukeList;
import duke.ui.Ui;

/**
 * duke.Duke class serves as the chatbot
 */
public class Duke {

    private DukeList ItemList;
    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.ItemList = new DukeList(this.storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.ItemList = new DukeList();
        }

    }

    public static void main(String[] args) {
           new Duke("data/duke.txt").run();
    }

    /**
     * This method initialises the beginning of the bots interaction with the user. It analyses the users input
     * and performs the relevant action correspondingly
     */
    public void run() {
        //Initialising Scanner
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(ItemList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


}


