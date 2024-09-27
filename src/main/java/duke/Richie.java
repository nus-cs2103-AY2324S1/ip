package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import javafx.application.Platform;

//@@author ruishanteo-reused
//Reused from https://github.com/ruishanteo/ip
// with minor modifications
/**
 * Entry point class for the Richie application
 */
public class Richie {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for a Richie application
     * @param filePath String representing the filePath of the data text file
     */
    public Richie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (RichieException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns the initialisation message of the Richie application
     */
    public String init() {
        ui.showWelcome();
        return ui.getCurrentMessage();
    }

    /**
     * Takes in the user input as a string and returns the chatbot response as a string
     */
    public String getResponse(String userInput) {
        try {
            Command command = Parser.parse(userInput);
            command.execute(this.ui, this.storage, this.tasks);
            if (command instanceof ExitCommand) {
                Platform.exit();
            }
        } catch (RichieException e) {
            ui.showMessage(e.getMessage());
        }
        return ui.getCurrentMessage();
    }
}
//@ruishanteo

