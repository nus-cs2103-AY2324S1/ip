package noac;

import noac.command.Command;

/**
 * Main class of the Noac Chatbot.
 */
public class Noac {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialise the storage, tasklist and ui objects.
     *
     * @param filePath File path for where to save the save file.
     */
    public Noac(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NoacException e) {
            ui.showErrorMessage(e);
            tasks = new TaskList();
        }
    }

    /**
     * Function to start the chatbot, run the loop and handle exits.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NoacException e) {
                ui.showErrorMessage(e);
            }
        }
    }

    /**
     * Main function where the program runs.
     *
     * @param args input for main.
     */
    public static void main(String[] args) {
        new Noac("data/tasks.txt").run();
    }

}



