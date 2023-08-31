import java.time.format.DateTimeFormatter;

public class Duke {
    private String name = "WallE";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
    public void run() {
        ui.printGreeting(this.name);
        runCommandLoopUntilByeCommand();
    }

    /** Reads the user command and executes it, until the user issues the bye (exit) command.  */
    private void runCommandLoopUntilByeCommand() {
        Command command = null;
        do {
            try {
                String userCommandText = ui.getUserCommand();
                ui.printDivider();
                command = new Parser().parseCommand(userCommandText);
                command.execute(tasks, ui, storage);
                storage.save(tasks);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printDivider();
            }
        } while (!ExitCommand.isExit(command));
    }
}
