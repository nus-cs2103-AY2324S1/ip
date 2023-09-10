import java.io.FileNotFoundException;

/**
 * A chatbot named Bert that interacts with the user
 * and keeps track of a task list.
 */
public class Bert {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Bert(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = new Parser().parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BertException e) {
                ui.showError(e.getMessage());
            }
        }
        storage.save(tasks);
    }

    public static void main(String[] args) {
        new Bert("data/tasks.txt").run();
    }
}
