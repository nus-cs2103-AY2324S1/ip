import commands.Command;
import exceptions.CommandNotRecognizedException;
import exceptions.NoCommandDetailException;
import exceptions.StorageException;
import exceptions.TimeParsingException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

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
                    ui.showCommandNotRecognized();
                } catch (NoCommandDetailException e) {
                    ui.showNoCommandDetail();
                } catch (TimeParsingException e) {
                    ui.showTimeParsingError();
                } catch (StorageException e) {
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
