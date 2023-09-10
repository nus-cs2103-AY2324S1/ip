package main;
import commands.Command;
import components.DukeException;
import components.Parser;
import components.Storage;
import components.Ui;
import tasks.TaskList;

public class Duke {
    private final Storage storage;
    private TaskList list;
    private final Ui ui;
    private static final String chatBotName = "CHAD CCP";

    /**
     * Constructor for Duke.
     * @param PARENT_DIR Parent directory of the file.
     * @param FILEPATH Filepath of the file.
     */
    public Duke(String PARENT_DIR, String FILEPATH) {
        ui = new Ui();
        storage = new Storage(PARENT_DIR, FILEPATH);
        try {
            storage.loadOrCreateFile();
            list = storage.readData(storage);
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

    /**
     * Drives the program by reading user input and executing the command.
     */
    public void run() {
        ui.showWelcome(chatBotName);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                //ui.showLine(); 
                Command c = Parser.parse(fullCommand);
                c.execute(list, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    /**
     * Main method to run the program.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke("./data", "./data/store.txt").run();
    }
}
