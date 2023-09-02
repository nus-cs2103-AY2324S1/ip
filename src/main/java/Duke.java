import commands.Command;
import functional.Storage;
import tasks.Task;

public class Duke {
    private Storage storage;
    private functional.TaskList<Task> tasks;
    private functional.Ui ui;
    private String filePath;

    public Duke(String filePath) {
        ui = new functional.Ui();
        storage = new Storage(filePath);
        this.filePath = filePath;
        try {
            tasks = new functional.TaskList<Task>(storage.load());
        } catch (functional.DukeException e) {
            ui.showLoadingError();
            tasks = new functional.TaskList<Task>();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = functional.Parser.parse(fullCommand);
                c.execute(tasks, ui, false, false);
                isExit = c.isExit();
            } catch (functional.DukeException e) {
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
