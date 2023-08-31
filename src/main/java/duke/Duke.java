package duke;

import duke.commands.Command;
import duke.exceptions.CommandDetailException;
import duke.exceptions.CommandNotRecognizedException;
import duke.exceptions.StorageException;
import duke.exceptions.TimeParsingException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private static final String DATA_FILE_PATH = "./data/duke.txt";
    private static final Storage storage = new Storage(DATA_FILE_PATH);
    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser();

    public static void main(String[] args) {
        ui.showLine();
        ui.showWelcome();
        TaskList tasks;
        try {
            tasks = storage.load();

            boolean isExit = false;
            while (!isExit) {
                try {
                    String input = ui.readCommand();
                    ui.showLine();
                    Command c = parser.parse(input);
                    c.execute(tasks, ui, storage);
                    isExit = c.isExit();
                } catch (CommandNotRecognizedException e) {
                    ui.showError(e);
                    ui.showCommandNotRecognized();
                } catch (CommandDetailException e) {
                    ui.showError(e);
                    ui.showNoCommandDetail();
                } catch (TimeParsingException e) {
                    ui.showError(e);
                    ui.showTimeParsingError();
                } catch (StorageException e) {
                    ui.showError(e);
                    ui.showLoadingError();
                } finally {
                    ui.showLine();
                }
            }
        } catch (StorageException e) {
            ui.showLoadingError();
        } finally {
            ui.showLine();
        }
    }

}
