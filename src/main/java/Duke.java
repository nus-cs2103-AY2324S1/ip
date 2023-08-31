import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private final Ui ui;

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

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
