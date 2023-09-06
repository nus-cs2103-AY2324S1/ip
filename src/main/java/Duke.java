import commands.Command;
import functional.*;
import tasks.Task;

public class Duke {
    private Storage storage;
    private TaskList<Task> tasks;
    private Ui ui;
    private String filePath;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.filePath = filePath;
        try {
            tasks = new TaskList<Task>(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList<Task>();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean hasExit = false;
        while (!hasExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, false, false);
                hasExit = c.hasExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        storage.save(tasks);
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
