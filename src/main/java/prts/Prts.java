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
     * Retrives the initialization message played when PRTS starts up.
     * @return The message to be displayed.
     */
    public String getInitialMessage() {
        return ui.showWelcome();
    }

    /**
     * Generates a response to the user input.
     */
    public String getResponse(String input) {
        try {
            Command c = CommandParser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (PrtsException e) {
            return e.getMessage();
        }

    }

}
