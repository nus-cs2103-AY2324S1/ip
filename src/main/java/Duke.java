import java.util.ArrayList;

/**
 * Main class that drives the Duke chatbot.
 */
public class Duke {

    protected Storage storage;
    protected TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui(this);
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.readFromDatabase(), this);
        } catch (DukeException e) {
            this.ui.showError(e);
            this.tasks = new TaskList(new ArrayList<Task>(), this);
        }
    }

    public void run() {
        ui.printIntro();
        ui.promptInput();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
