package prts;

import prts.command.Command;
import prts.command.CommandParser;

/**
 * A chatbot and to-do list that can receive text input from the user, parse it, and execute
 * predefined commands.
 */
public class Prts {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private String fileName = "prts.PRTS.txt";
    private String[] directories = {"data"};

    /**
     * Constructs a PRTS object, and initializes its fields.
     */
    public Prts() {
        ui = new Ui();
        storage = new Storage(fileName, directories);
        try {
            tasks = storage.load();
        } catch (CreateNewSaveException | NewSaveFailedException e) {
            ui.displayMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main program loop of PRTS.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showSoftLine(); // show the divider line ("_______")
                Command c = CommandParser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (PrtsException e) {
                ui.displayMessage(e.getMessage());
            } finally {
                if (!isExit) {
                    ui.showHardLine();
                }
            }
        }
        ui.showGoodbye();
    }

    /**
     * Runs PRTS.
     * @param args Unused argument.
     */
    public static void main(String[] args) {
        new Prts().run();
    }

}
