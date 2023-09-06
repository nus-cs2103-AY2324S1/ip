package duke;

import java.io.IOException;
import java.util.ArrayList;

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

    public DataFile getDataFile() {
        return dataFile;
    }

    public TaskList getTaskList() {
        return taskList;
    }
}
