package functions;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import commands.Command;
import commands.LoadDeadlineCommand;
import commands.LoadEventCommand;
import commands.LoadToDoCommand;
/**
 * A utility class for loading a file.
 */
public class Load {

    private TaskList taskList;
    private String loadFilePath;

    /**
     * A public constructor to initialize a new Load instance
     *
     * @param loadFilePath file path to load
     */
    public Load(String loadFilePath) {
        this.loadFilePath = loadFilePath;
        this.taskList = new TaskList();
    }

    /**
     * Loads the tasks in a file and returns the previously saved version of a Task List.
     *
     * @return A loaded TaskList
     */
    public TaskList load() throws IOException {
        FileReader f = null;

        // Checks if file exists.
        try {
            f = new FileReader(loadFilePath);
        } catch (IOException e) {
            // Creates new taskList
            return this.taskList;
        }

        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String currentTaskAsString = s.nextLine();
            String taskType = currentTaskAsString.substring(1, 2).toUpperCase();

            Command command = null;
            String commandResult;

            switch (taskType) {
            case "T":
                command = new LoadToDoCommand(currentTaskAsString, this.taskList);
                commandResult = command.execute();
                break;

            case "D":
                command = new LoadDeadlineCommand(currentTaskAsString, this.taskList);
                commandResult = command.execute();
                break;

            case "E":
                command = new LoadEventCommand(currentTaskAsString, this.taskList);
                commandResult = command.execute();
                break;

            default:
                commandResult = "Error";
            }

            assert commandResult == "Ok" : "Error in loading task";

        }
        f.close();
        return this.taskList;
    };
}
