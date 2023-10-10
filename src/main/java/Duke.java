import java.io.File;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(new File("data/tasks.txt"));
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcomeMessage();
        ui.run();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
