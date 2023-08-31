package duke;

import duke.commands.Command;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.UI;

import java.io.IOException;
import java.time.format.DateTimeParseException;

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
        ui.showWelcome();
        boolean isExit = false;
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
                ui.printMessage("Error: Task number must be an integer.\n(example: mark 1)");
            } catch (DateTimeParseException exception) {
                ui.printMessage("Invalid Datetime Format: it should be dd-mm-yyyy hh:mm!");
            }
        } while (!isExit);
    }
}