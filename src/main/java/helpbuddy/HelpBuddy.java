package helpbuddy;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import helpbuddy.command.Command;
import helpbuddy.exception.HelpBuddyException;
import helpbuddy.storage.Storage;
import helpbuddy.task.TaskList;
import helpbuddy.ui.Parser;
import helpbuddy.ui.Ui;

/**
 * A HelpBuddy class creates a HelpBuddy object to run the HelpBuddy chatbot.
 */

public class HelpBuddy {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new HelpBuddy object containing the specified String filePath to store data.
     * @param filePath the filePath for data to be loaded and saved.
     */
    public HelpBuddy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (HelpBuddyException e) {
            ui.printErrorMessage(e.getMessage());
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Runs the HelpBuddy chatbot.
     */
    public void run() {
        ui.printHelloMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readInput();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (HelpBuddyException e) {
                ui.printErrorMessage(e.getMessage());
            } catch (IOException e) {
                ui.printErrorMessage(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.printDateTimeParseErrorMessage();
            }
        }
    }
}
