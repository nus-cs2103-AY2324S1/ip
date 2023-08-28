package spot;

import spot.command.Command;
import spot.exception.SpotException;

/**
 * Represents a chatbot named Spot.
 */
public class Spot {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a new Spot object.
     */
    public Spot() {
        try {
            ui = new Ui();
            storage = new Storage();
            tasks = new TaskList(storage.loadTasks());
        } catch (SpotException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Starts running the Spot chatbot.
     */
    public void run() {
        ui.sayHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (SpotException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Starts up the Spot chatbot.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Spot().run();
    }
}
