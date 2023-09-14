package functions;

import commands.Command;
import commands.LoadDeadlineCommand;
import commands.LoadEventCommand;
import commands.LoadToDoCommand;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.FileReader;

/**
 * A utility class for loading a file.
 */
public class Load {

    TaskList taskList;
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

        try {
            f = new FileReader(loadFilePath);
        } catch (IOException e) {
            System.out.println("File does not exist in directory.");
            return null;
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

            assert commandResult == "Ok";

        }
        f.close();
        return this.taskList;
    };
}
