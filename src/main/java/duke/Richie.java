package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import javafx.application.Platform;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CompletableFuture;

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


    public String init() {
        ui.showWelcome();
        return ui.getCurrentMessage();
    }

    public String getResponse(String userInput) {
        try {
            Command command = Parser.parse(userInput);
            command.execute(this.ui, this.storage, this.tasks);
            if (command instanceof ExitCommand) {
                //exit program
                // make sure the program will show the exit message before exiting the platform
                Platform.exit();
            }
        } catch (RichieException e) {
            ui.showMessage(e.getMessage());
        }
        return ui.getCurrentMessage();
    }

    /**
     * Entry point to the Richie application
     * @param args not used in the application
     */
}

