package duke;

import java.io.File;
import java.io.IOException;

import duke.commands.Command;
import duke.tools.Parser;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;


/**
 * Encapsulates the logic of a Chat bot
 *
 * @author Rayson
 */
public class Duke {

    // CONSTANTS
    private static final String DIR_NAME = "data";
    private static final String FILE_NAME = "duke.txt";

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        TaskList tasks1;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks1 = new TaskList(storage.loadTasksFromStorage());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks1 = new TaskList();
        }
        tasks = tasks1;
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit && ui.checkForCommand()) { // need to check whether scanner has a next line
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);

                c.execute(tasks, ui, storage);
                isExit = c.isExit();

            } catch (Exception e) {
                ui.showErrorMessage(e);
            }
        }
    }


    public static void main(String[] args) {
        new Duke(DIR_NAME + File.separator + FILE_NAME).run();
    }
}
