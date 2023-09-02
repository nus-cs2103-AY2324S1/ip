package Duke;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Parser parser;
    public static void main (String[] args) throws DukeException {
        new Duke("file.txt").run();
    }

    public Duke (String filename) {
        ui = new Ui();
        storage = new Storage(filename);
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String cmd = ui.readCommand();
            parser.parse(cmd, tasks, storage, ui);
            isExit = parser.isExit();
        }
        ui.showExit();
    }

}
