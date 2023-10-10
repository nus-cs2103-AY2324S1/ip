
package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * This class handles the data reads from Duke.txt file to load tasks
 */
public class Storage {
    /**
     * Reads data from file and loads into ArrayList if available. Else creates the file.
     *
     * @param pathOfDirectory Path variable provided
     * @param storeTask ArrayList which stores tasks when program is running
     * @throws IOException if an I/O error occurs
     */
    public static void readFromDisk(Path pathOfDirectory, ArrayList<Task> storeTask) throws IOException {
        Files.createDirectories(pathOfDirectory.getParent());
        handleFileAccess(pathOfDirectory, storeTask);
    }
    private static void handleFileAccess(Path pathOfDirectory, ArrayList<Task> storeTask) throws IOException {
        if (Files.exists(pathOfDirectory)) {
            readFromFile(pathOfDirectory, storeTask);
        } else {
            createNewFile(pathOfDirectory);
        }
    }
    private static void readFromFile(Path pathOfDirectory, ArrayList<Task> storeTask) throws IOException {
        List<String> fileLines = Files.readAllLines(pathOfDirectory);
        for (String task : fileLines) {
            //since "|" is a special character, use "//"
            String[] taskVariablesTemp = task.split("\\|");
            handleTaskTypes(taskVariablesTemp, storeTask);
        }
    }
    private static void handleTaskTypes(String[] taskVariablesTemp, ArrayList<Task> storeTask) {
        String taskType = taskVariablesTemp[0];
        switch(taskType) {
        case "T":
            storeTask.add(new ToDo(Integer.parseInt(taskVariablesTemp[1]), taskVariablesTemp[2]));
            break;
        case "D":
            storeTask.add(new Deadline(
                    Integer.parseInt(taskVariablesTemp[1]), taskVariablesTemp[2], taskVariablesTemp[3]));
            break;
        case "E":
            storeTask.add(new Event(Integer.parseInt(
                    taskVariablesTemp[1]), taskVariablesTemp[2], taskVariablesTemp[3], taskVariablesTemp[4]));
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + taskType);
        }
    }
    private static void createNewFile(Path pathOfDirectory) throws IOException {
        Files.createFile(pathOfDirectory);
    }
}
