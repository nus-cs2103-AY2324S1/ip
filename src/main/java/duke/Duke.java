package duke;

import duke.storage.Storage;
import duke.ui.TextUi;

import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        TaskList tasks;
        TextUi ui = new TextUi();
        Storage storage = new Storage();
        Parser parser = new Parser();

        ui.showWelcomeMessage();

        try {
            tasks = storage.load();
            if (tasks.size() == 0) {
                ui.showMessage(String.format("No stored tasks found from %s", Storage.DEFAULT_STORAGE_PATH),
                        "Starting from an empty task list.");
                tasks = new TaskList();
            } else {
                ui.showMessage(String.format("Tasks loaded from %s", Storage.DEFAULT_STORAGE_PATH));
            }
        } catch (IOException e) {
            ui.showMessage(String.format("Error loading tasks from %s", Storage.DEFAULT_STORAGE_PATH),
                    "Starting from an empty task list.");
            tasks = new TaskList();
        }

        Command command;
        do {
            String input = ui.getUserCommand();
            command = parser.parseCommand(input);
            command.setData(tasks);
            String[] response = command.execute();
            ui.showMessage(response);

            try {
                storage.save(tasks);
            } catch (IOException e) {
                ui.showMessage("Error saving tasks");
            }
        } while (!command.isBye());
    }
}
