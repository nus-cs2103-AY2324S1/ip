package duke;

import command.Command;
import command.ExitCommand;
import javafx.stage.Stage;

/**
 * Represents the command line application Duke.
 */
public class Duke {
    private DiskManager diskManager;
    private TaskManager taskManager;
    private Stage stage;


    /**
     * Constructs Duke with the directory path and file name that specifies the filepath
     * of data Duke should run on.
     *
     * @param directoryPath The directory path.
     * @param fileName The file name.
     */
    public Duke(String directoryPath, String fileName, Stage stage) {
        this.stage = stage;
        this.diskManager = new DiskManager(directoryPath, fileName);
        try {
            this.taskManager = this.diskManager.loadFromDisk();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            this.taskManager = new TaskManager();
        }

        assert this.stage != null : "stage of Duke instance should not be null";
        assert this.taskManager != null : "taskManager of Duke instance should not be null";
        assert this.diskManager != null : "diskManager of Duke instance should not be null";
    }

    public String execute(String input) {
        try {
            Command c = Parser.parseCommand(input);
            if (c.isExit()) {
                stage.close();
            }
            String res = c.execute(taskManager, diskManager);
            return res;
        } catch (DukeException e) {
            return e.getMessage();
        }

    }
}
