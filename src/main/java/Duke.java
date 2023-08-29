import java.io.IOException;
import java.nio.file.Paths;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.loadData());
        } catch (IOException e) {
            ui.showErrorMessage("Something went wrong with loading the tasks", e);
            this.tasks = new TaskList();
        }
    }

    public void run() {
        boolean isRunning = true;

        ui.showWelcomeMessage();
        if (tasks.getSize() != 0) {
            ui.showLoadedTasksMessage();
        }

        while (isRunning) {
            Command command = Parser.parse(ui.readInput());
            command.execute(tasks, ui, storage);
            isRunning = !command.isExit();
        }
    }

    public static void main(String[] args) {
        new Duke(Paths.get("data", "duke.txt").toAbsolutePath().toString()).run();
    }
}
