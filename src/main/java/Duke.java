import java.io.*;
import java.time.format.DateTimeParseException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Duke(String filePath) throws DukeException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadTasks());
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.scan();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException | DateTimeParseException | IndexOutOfBoundsException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws DukeException, FileNotFoundException {
            new Duke("data/tasks.txt").run();
    }
}
