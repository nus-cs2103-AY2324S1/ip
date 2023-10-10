package skye;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import skye.commands.Command;
import skye.data.ListManager;
import skye.data.exception.DukeException;
import skye.data.exception.DukeExceptionType;
import skye.parser.Parser;
import skye.storage.StorageManager;
import skye.ui.UI;

/**
 * Represents the main program instance of the Skye chatbot.
 * <p>
 * The program instance depends on the Parser, Storage, TaskList and UI classes to function.
 */
public class Skye {

    private Parser parser;
    private StorageManager storageManager;
    private ListManager listManager;
    private UI ui;
    private boolean isExit;

    /**
     * Initializes an instance of the Skye chatbot.
     *
     * @param saveDirectory File path to the directory containing the save file.
     */
    public Skye(String saveDirectory) {
        parser = new Parser();
        storageManager = new StorageManager(saveDirectory);
        ui = new UI();
        isExit = false;
        try {
            listManager = new ListManager(storageManager);
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
        }
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Gets the response from the chatbot.
     *
     * @param input User input
     * @return Response from the chatbot.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            isExit = command.isExit();
            return command.execute(listManager, ui, storageManager);
        } catch (DukeException | IOException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return DukeExceptionType.INVALID_DATETIME_FORMAT.getMessage();
        }
    }
}
