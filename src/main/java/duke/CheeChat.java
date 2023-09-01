package duke;

import duke.command.Command;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This class implements the CheeChatbot.
 * The chatbot reads and stores tasks that users input.
 * It will run until the "bye" command is read.
 */
public class CheeChat {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the CheeChat class
     * @param filePath the filePath of the file that stores the tasks that users input.
     */
    public CheeChat(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }


    public static void main(String[] args) {
        new CheeChat("data/duke.txt").run();
    }


    /**
     * Run the chatbot until the "bye" command is read.
     * Will store tasks in a file and read tasks stored in the file beforehand.
     * Print the tasks added in the TaskList.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}

