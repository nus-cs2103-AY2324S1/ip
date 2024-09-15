package kevin;

import kevin.commands.Command;
import kevin.exceptions.CommandDetailException;
import kevin.exceptions.CommandNotRecognizedException;
import kevin.exceptions.StorageException;
import kevin.exceptions.TimeParsingException;
import kevin.storage.Storage;
import kevin.task.TaskList;
import kevin.ui.Ui;

//varargs inclusion seems to be not applicable.
/**
 * Represents the main class for the Duke CLI application.
 */
public class Kevin {

    private static final String DATA_FILE_PATH = "./data/duke.txt";
    private static final Storage storage = new Storage(DATA_FILE_PATH);
    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser();


    /**
     * The main entry point for the Duke application.
     *
     * @param args
     */
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


    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            return c.executeGui(storage.load(), ui, storage);
        } catch (CommandNotRecognizedException e) {
            return ui.showErrorGui(e) + ui.showCommandNotRecognizedGui();
        } catch (CommandDetailException e) {
            return ui.showErrorGui(e) + ui.showNoCommandDetailGui();
        } catch (TimeParsingException e) {
            return ui.showErrorGui(e) + ui.showTimeParsingErrorGui();
        } catch (StorageException e) {
            return ui.showErrorGui(e) + ui.showLoadingErrorGui();
        }
    }

    public String getWelcome() {
        return ui.showWelcomeGui();
    }
}
