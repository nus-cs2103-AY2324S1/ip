package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.commands.Command;
import duke.parser.Parser;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor to initialize Duke.
     *
     * @param filePath the path of the .txt file to be loaded
     */
    public Duke(String filePath)  {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (ParseException e) {
            ui.showError(e.getMessage());
        }
    }

    /** Executes Duke */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (ParseException e) {
                ui.showInvalidFormat();
            } catch (IOException e) {
                ui.showWriteFileError();
            } catch (IllegalArgumentException e) {
                ui.showInvalidCommand();
            }
            finally {
                ui.showLine();
            }
        }
    }

    /**
     * Start point of Duke.
     *
     * @param args arguments passed in by user.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
