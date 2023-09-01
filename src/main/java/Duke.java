import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.getTaskList());
    }

    public void run() {
        try {
            this.ui.intro();
            this.storage.read();
            this.tasks.handleInput();
            this.storage.write(storage.getTaskList());
            this.ui.outro();
        } catch (IOException e) {
            this.ui.showLoadingError(e);
        } catch (DukeException exc) {
            this.ui.showLoadingError(exc);
        }
    }


    public static void main(String[] args) throws DukeException {
        new Duke("data/tasks.txt").run();
    }
}
