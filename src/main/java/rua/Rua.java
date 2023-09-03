package rua;

import rua.command.Command;
import rua.common.Parser;
import rua.common.Storage;
import rua.common.Ui;
import rua.task.TaskList;

public class Rua {

    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Constructs a chatbot Rua object.
     *
     * @param filePath The path to the file which stores the tasks.
     */
    public Rua(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            ui.showMessage("Load successfully. Now you have " + tasks.getTasks().size()
                    + " tasks in the list.\n");
            ui.showMessage(tasks.toString());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Rua("src/main/data/tasks.txt").run();
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                tasks = c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.toString());
            } finally {
                ui.showLine();
            }
        }
    }
}
