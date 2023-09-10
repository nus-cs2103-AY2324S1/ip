package bareum;

import bareum.commands.ByeCommand;
import bareum.commands.Command;
import javafx.stage.Stage;

/**
 *  This class implements the Bareum chatbot which keeps track of tasks to do.
 */

public class Bareum {
    private Stage stage;
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
     * @param stage Stage for GUI.
     */
    public Bareum(Stage stage) {
        this.stage = stage;
        this.storage = new Storage("./data/storedTasks.txt");
        this.taskList = new TaskList();
        storage.loadSavedTaskList(taskList);
        this.ui = new Ui();
    }

    /**
     * Run the instance of Bareum and get user input until the user uses the 'bye' command.
     */
    public String run(String input) {
        try {
            Command cmd = Parser.parse(input);
            String response = cmd.execute(ui, storage, taskList);

            if (cmd instanceof ByeCommand) {
                stage.close();
            }
            return response;
        } catch (BareumException e) {
            return e.getMessage();
        }
    }
}