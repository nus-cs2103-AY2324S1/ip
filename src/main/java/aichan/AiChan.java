package aichan;

import aichan.command.Command;

/**
 * Represents the main class of the chatbot AiChan.
 */
public class AiChan {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an AiChan object with the given filePath.
     * Initialize user interface, storage, and task lists.
     *
     * @param filePath File path for storing and loading tasks.
     */
    public AiChan(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (AiChanException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of application AiChan.
     * Greets user, receives commands from user and executes it.
     * Terminates when user enter "bye".
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AiChanException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Starts the program by creating an AiChan instance.
     * Runs the AiChan instances.
     *
     * @param args The command line arguments (not used).
     */
    public static void main(String[] args) {
        // System.out.println("hello world");
        new AiChan("data/tasks.txt").run();
    }
}
