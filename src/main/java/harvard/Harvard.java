package harvard;
public class Harvard {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Harvard(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
        parser = new Parser();
    }

    public void run() {
        ui.displayWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.displayError(e);
            }
        }
    }
    public static void main(String[] args) {
        new Harvard("data/tasks.txt").run();
    }
}
