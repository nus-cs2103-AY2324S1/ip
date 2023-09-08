package main;
import commands.Command;
import components.DukeException;
import components.Parser;
import components.Storage;
import components.Ui;
import tasks.TaskList;

public class Duke {
    private Storage storage;
    private TaskList list;
    private Ui ui;
    private static final String chatBotName = "CHAD CCP";

    public Duke(String PARENT_DIR, String FILEPATH) {
        ui = new Ui();
        storage = new Storage(PARENT_DIR, FILEPATH);
        try {
            storage.loadOrCreateFile();
            list = storage.readData();
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

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
            } finally {
                //ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data", "./data/store.txt").run();
    }
}
