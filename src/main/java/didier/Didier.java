package didier;

import didier.command.Command;
import didier.exception.DidierException;

/**
 * Represents a Didier bot that is able to interact with a user, keep track of the list of tasks
 * of the user and save the data in local storage.
 */
public class Didier {

    private Storage storage;
    private TaskList taskList;
    private UI ui;

    /**
     * The constructor for the Didier bot.
     *
     * @param directoryPath The path to the directory where Didier should store the task list.
     * @param fileName The name of the file where Didier should store the task list.
     */
    public Didier(String directoryPath, String fileName) {
        ui = new UI();
        storage = new Storage(directoryPath, fileName);
        taskList = storage.getTasks();
    }

    /**
     * The main entry point for the user interaction with Didier to begin.
     */
    public void run() {
        this.ui.botGreet();
        boolean isExit = false;
        while (!isExit) {
            // Carry out the action determined by the didier.command
            try {
                String commandString = this.ui.readCommand();
                Command command = Parser.parse(commandString);
                command.execute(this.taskList, this.ui, this.storage);
                isExit = command.isExit();
            } catch (DidierException exception) {
                this.ui.botPrintError(exception);
            } finally {
                if (!isExit) {
                    this.ui.botEndCommand();
                }
            }
        }
        this.ui.botGoodBye();
    }


    public static void main(String[] args) {
        Didier didier = new Didier("data/", "didier.txt");
        didier.run();
    }



}
