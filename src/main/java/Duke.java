import java.time.LocalDate;
import java.util.Scanner;

public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadFile();
        } catch (ChatException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public void run() throws ChatException {
        ui.greetResponse();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ChatException e) {
                ui.showLoadingError(e);
            }
        }
    }

    public static void main(String[] args) throws ChatException {
        new Duke("data/duke.txt").run();
    }
}
