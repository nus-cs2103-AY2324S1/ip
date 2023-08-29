public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private Parser parser;

    public Duke(String saveLocation) {
        ui = new Ui();
        storage = new Storage(saveLocation, ui);
        try {
            tasks = new TaskList(storage.loadSavedTasks(), ui);
            parser = new Parser(ui, tasks);
        } catch (DukeNoExistingTasksException e) {
            tasks = new TaskList(ui);
            parser = new Parser(ui, tasks);
        } catch (DukeLoadTasksException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList(ui);
            parser = new Parser(ui, tasks);
        }
    }
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    private void run() {
        ui.showWelcome();
        String cmd = ui.readCmd();
        while (parser.parseCommand(cmd)) {
            cmd = ui.readCmd();
            storage.saveTasks(tasks);
        }
        ui.closeScanner();
    }
}
