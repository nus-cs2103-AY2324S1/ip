package dude;

import dude.command.DudeCommand;
import dude.exception.DudeException;
import dude.task.TaskList;

/**
 * Dude (Duke, but renamed).
 */
public class Dude {
    private Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Dude.
     *
     * @param filePath Path to save file on disk.
     */
    public Dude(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DudeException e) {
            ui.printMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Dude dude = new Dude("./data/dude.txt");
        dude.run();
    }

    /**
     * Runs Dude.
     */
    public void run() {
        ui.printHello();
        // Input loop -- wait for input, respond, repeat
        boolean shouldContinue = true;
        while (shouldContinue) {
            try {
                String input = ui.readInput();
                DudeCommand c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                shouldContinue = !c.isExit();
            } catch (DudeException e) {
                ui.printMessage(e.getMessage());
            }
        }
    }
}
