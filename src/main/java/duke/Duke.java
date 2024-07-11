package duke;

import java.io.IOException;
import java.util.ArrayList;

import commands.Command;
import parser.Parser;
import storage.DataFile;
import tasks.Task;
import tasks.TaskList;


/**
 * The programme that runs the Duke chatbot.
 */
public class Duke {

    private final DataFile dataFile;
    private TaskList taskList;

    /**
     * Duke constructor that takes a String, String and initialises
     * class variables.
     * @param filePath Name of the path.
     * @param fileName Name of the file.
     */
    public Duke(String filePath, String fileName) {
        dataFile = new DataFile(filePath, fileName);
        try {
            taskList = new TaskList(dataFile.fileToObjects());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            taskList = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Returns the intended response after reading the command.
     * @param input User's input
     * @return Returns the intended response after reading the command.
     */
    public String response(String input) {
        Command c = new Parser().getCommand(input);
        return c.execute(taskList, dataFile);
    }
}
