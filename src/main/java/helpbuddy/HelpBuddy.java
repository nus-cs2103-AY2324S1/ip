package helpbuddy;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import helpbuddy.command.Command;
import helpbuddy.exception.HelpBuddyException;
import helpbuddy.storage.Storage;
import helpbuddy.task.TaskList;
import helpbuddy.ui.Parser;
import helpbuddy.ui.Ui;
import javafx.scene.image.Image;

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
     * @throws IOException if Storage encounters error in creating database or reading entry from file.
     * @throws HelpBuddyException if the task entries in file has missing fields.
     */
    public HelpBuddy(String filePath) throws IOException, HelpBuddyException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadData());
    }

    /**
     * Gets a response from HelpBuddy chatbot after user's input.
     * @param userInput the String that represents user input.
     * @return a String message of HelpBuddy's response.
     */
    public String getResponse(String userInput) {
        try {
            Command c = Parser.parseCommand(userInput);
            return c.execute(tasks, ui, storage);
        } catch (HelpBuddyException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return ui.printDateTimeParseErrorMessage();
        }
    }

    /**
     * Says hello message when HelpBuddy is first launched.
     * @return a String of HelpBuddy's welcome message.
     */
    public static String sayHello() {
        return "Hello! I'm HelpBuddy.\n" + "What can I do for you?";
    }
}
