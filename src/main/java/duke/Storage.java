package duke;
import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the data reads from Duke.txt file to load tasks
 */

public class Storage {
    /**
     * Reads data from file and loads into ArrayList if available. Else creates the file.
     *
     * @param pathOfDirectory Path variable provided
     * @param storeTask ArrayList<Task> which stores tasks when program is running
     * @throws IOException if an I/O error occurs
     */
    public static void readFromDisk(Path pathOfDirectory, ArrayList<Task> storeTask) throws IOException {
        Files.createDirectories(pathOfDirectory.getParent());
        if (Files.exists(pathOfDirectory)) {
            System.out.println("Please wait while your file opens...");
            List<String> fileLines = Files.readAllLines(pathOfDirectory);
            for (String task : fileLines) {
                String[] taskVariablesTemp = task.split("\\|");     //since "|" is a special character, use "//"
                if (taskVariablesTemp[0].equals("T")) {
                    storeTask.add(new ToDo(Integer.parseInt(taskVariablesTemp[1]), taskVariablesTemp[2]));
                } else if (taskVariablesTemp[0].equals("D")) {
                    storeTask.add(new Deadline(Integer.parseInt(taskVariablesTemp[1]), taskVariablesTemp[2], taskVariablesTemp[3]));
                } else if (taskVariablesTemp[0].equals("E")) {
                    storeTask.add(new Event(Integer.parseInt(taskVariablesTemp[1]), taskVariablesTemp[2], taskVariablesTemp[3], taskVariablesTemp[4]));
                } else {
                    System.out.println("Error...");
                }
            }
        } else {
            System.out.println("Your file does not exist. Creating...");
            Files.createFile(pathOfDirectory);
            System.out.println("File created.");
        }
    }
}