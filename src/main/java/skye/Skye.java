package skye;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import skye.commands.Command;
import skye.data.TaskList;
import skye.data.VenueList;
import skye.data.exception.DukeException;
import skye.parser.Parser;
import skye.storage.Storage;
import skye.ui.UI;

/**
 * Represents the main program instance of the Skye chatbot.
 * <p>
 * The program instance depends on the Parser, Storage, TaskList and UI classes to function.
 */
public class Skye {

    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private VenueList venueList;
    private UI ui;

    /**
     * Initializes an instance of Duke by providing the file path to the save file.
     *
     * @param filePath File path to the directory containing the save file.
     */
    public Skye(String filePath) {
        parser = new Parser();
        storage = new Storage(filePath);
        ui = new UI();
        venueList = new VenueList();
        try {
            taskList = new TaskList(storage.load());
            venueList = new VenueList(storage.loadVenues());
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
            venueList = new VenueList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            return command.execute(taskList, venueList, ui, storage);
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }

    }
}
