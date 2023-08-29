import commands.Command;
import data.TaskList;
import data.exception.DukeException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import parser.Parser;
import storage.Storage;
import ui.UI;

public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private UI ui;

    public Duke(String filePath) {
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
        new Duke("data/tasks.txt").run();
    }

    private void run() {
        // Welcome message
        ui.showWelcome();
        boolean isExit = false;
        // Program only exits when user enters "bye" command
        do {
            try {
                String fullCommand = ui.readCommand();
                System.out.println();
                Command command = parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException | IOException exception) {
                ui.printMessage(exception.getMessage());
            } catch (NumberFormatException exception) {
                ui.printMessage("Error: data.task.Task number must be an integer.\n(example: mark 1)");
            } catch (DateTimeParseException exception) {
                ui.printMessage("Invalid Datetime Format: it should be dd-mm-yyyy hh:mm!");
            }
        } while (!isExit);
    }
}