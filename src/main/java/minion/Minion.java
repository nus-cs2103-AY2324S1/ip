package minion;

import minion.commands.Command;
import minion.data.TaskList;
import minion.data.exception.MinionException;
import minion.parser.CommandParser;
import minion.storage.Storage;
import minion.ui.Ui;

import java.io.IOException;

/**
 * Represents the Minion chatbot.
 */
public class Minion {
    private TaskList tasks;
    private final Ui ui;
    private final Storage storage;

    /**
     * A constructor for the Minion chatbot.
     * @param filePath The file path of the file storing the task list.
     */
    public Minion(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Minion("data/tasks.txt").run();
    }

    /**
     * Driver function for main.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                Command c = CommandParser.parse(command);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (MinionException | IOException e) {
                ui.print(e.getMessage());
            }
        }
    }
}
