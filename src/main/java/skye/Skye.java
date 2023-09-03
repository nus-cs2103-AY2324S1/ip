package skye;

import skye.commands.Command;
import skye.data.TaskList;
import skye.data.exception.DukeException;
import skye.parser.Parser;
import skye.storage.Storage;
import skye.ui.UI;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents the main program instance of the Skye chatbot.
 * <p>
 * The program instance depends on the Parser, Storage, TaskList and UI classes to function.
 */
public class Skye {

    private Parser parser;
    private Storage storage;
    private TaskList taskList;
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
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Skye("data/tasks.txt").run();
    }

    /**
     * A method to start running the chatbot.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        do {
            try {
                String fullCommand = ui.readCommand();
                System.out.println();
                Command command = parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException exception) {
                ui.printMessage(exception.getMessage());
            } catch (IOException exception) {
                ui.printMessage("IO Exception: " + exception.getMessage());
            } catch (NumberFormatException exception) {
                ui.printMessage("Error: Task number must be an integer.\n(example: mark 1)");
            } catch (DateTimeParseException exception) {
                ui.printMessage("Invalid Datetime Format: it should be dd-mm-yyyy hh:mm!");
            }
        } while (!isExit);
    }
}