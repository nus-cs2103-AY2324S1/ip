package duke;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws DukeException {
        ui.greet(tasks);
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/duke.txt").run();
    }

}
