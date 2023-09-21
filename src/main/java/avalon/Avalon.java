package avalon;

import avalon.task.TaskList;
import avalon.utility.Parser;
import avalon.utility.Storage;
import avalon.utility.Ui;

/**
 * The main class for the Duke ChatBot (Avalon).
 */
public class Avalon {

    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;



    /**
     * Constructs an Avalon instance with the specified file path for task storage.
     */
    public Avalon() {
        this.ui = new Ui();
        this.storage = new Storage("src/main/data/Avalon.txt");
        this.taskList = new TaskList();
        storage.loadTasks(this.taskList);
    }

    public String getResponse(String input) {
        ui.clearOutput();

        return Parser.processCommand(input, taskList, storage, ui);
    }
}
