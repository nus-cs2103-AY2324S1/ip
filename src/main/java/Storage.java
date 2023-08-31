import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    Parser parser;

    public Storage(Parser parser) {
        this.parser = parser;
    }

    /**
     * Loads tasks from the data file and populates the task list with them.
     * If the data file doesn't exist, a new file is created.
     * Any errors during loading or task addition are caught and handled.
     */
    public ArrayList<Task> loadTasksFromFile() {
        try {
            File dataFile = Paths.get(ChatterChicken.PATH).toAbsolutePath().toFile();
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(dataFile));
                String currLine = reader.readLine();
                ArrayList<Task> taskList = new ArrayList<>();
                while (currLine != null) {
                    String taskType = currLine.substring(0, currLine.indexOf(' '));
                    Task task = parser.parseTask(taskType, currLine);
                    taskList.add(task);
                    currLine = reader.readLine();
                }
                reader.close();
                return taskList;
            }
        } catch (IOException e) {
            System.err.println("An error occurred while loading tasks from file: " + e.getMessage());
        } catch (CCException e) {
            System.err.println("An error occurred while adding tasks: " + e.getMessage());
        }
        return null;
    }

    /**
     * Saves the provided task to the data file in its original user input format.
     *
     * @param task The task to be saved to the data file.
     */
    public static void saveTaskToFile(Task task) {
        try {
            File dataFile = Paths.get(ChatterChicken.PATH).toAbsolutePath().toFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile, true));
            writer.append(task.getTaskDescription() + "\n");
            writer.close();
        } catch (IOException e) {
            System.err.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

}
