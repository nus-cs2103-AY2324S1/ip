
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            throw new DukeException(Ui.loadingError(e.getMessage()));
        }
    }

    public void run() {
        ui.showWelcome();
        ui.showCommandGuide();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(Ui.error(e.getMessage()));
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) throws DukeException {
        new Duke("./data/duke.txt").run();
    }
}
