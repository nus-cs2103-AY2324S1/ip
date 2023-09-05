package bareum;

import bareum.commands.ByeCommand;
import bareum.commands.Command;

/**
 *  This class implements the Bareum chatbot which keeps track of tasks to do.
 */

public class Bareum {
    /**
     * The object that loads and saves tasks into the file
     */
    private Storage storage;
    /**
     * The object that manipulates the task list.
     */
    private TaskList taskList;
    /**
     * The object that manages interactions with the user.
     */
    private Ui ui;

    /**
     * Creates a new instance of Bareum.
     * @param filePath File path to the file that previously saved tasks will be retrieved from.
     */
    public Bareum(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
        this.ui = new Ui();
    }

    /**
     * Run the instance of Bareum and get user input until the user uses the 'bye' command.
     */
    public void run() {
        boolean isExit = false;

        storage.loadSavedTaskList(taskList);
        ui.showWelcomeMessage();

        while (!isExit) {
            try {
                ui.showLine();
                String input = ui.getUserInput();
                Command cmd = Parser.parse(input);
                cmd.execute(ui, storage, taskList);
                if (cmd instanceof ByeCommand) {
                    isExit = true;
                }
            } catch (BareumException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Starts the program and creates an instance of Bareum to run.
     *
     * @param args Command line arguments which are not currently used.
     */
    public static void main(String[] args) {
        new Bareum("./data/storedTasks.txt").run();
    }
}
