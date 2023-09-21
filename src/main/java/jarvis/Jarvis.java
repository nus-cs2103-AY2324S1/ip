package jarvis;

import jarvis.command.Command;
import jarvis.exception.JarvisException;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

public class Jarvis {

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList tasks;

    // Initialize the classes in the Duke constructor.
    public Jarvis(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
        this.parser = new Parser();
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JarvisException e) {
                ui.display(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Jarvis("./data/jarvis.txt").run();
    }
}