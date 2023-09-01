package duck;

import duck.task.TaskList;
import duck.ui.Ui;

/**
 * This class is command-line application responsible for managing tasks.
 */
public class Duck {
    private Ui ui;
    private final IoHandler ioHandler = new IoHandler();
    private final TaskList taskList;
    private Parser parser = new Parser();
    private final Storage storage;
    /**
     * Constructs a simple Duck Object.
     * @param filePath Stores the file path for loading and saving.
     */
    public Duck(String filePath) {
        ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.load());
    }
    /**
     * It runs the Duck Application.
     * Displays Contents, handle the user inputs and commands until user exits.
     */
    public void run() {
        ioHandler.welcomeMessage();
        boolean exit = false;
        while (!exit) {
            exit = !parser.parse(ioHandler.typeMessage(), ioHandler, taskList, storage);
        }
    }

    public static void main(String[] args) {
        new Duck("data/duck.txt").run();
    }
}
