package duke;

import duke.task.Event;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;
import duke.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Storage class handles the loading and saving of tasks to a specified file.
 */
public class Storage {
    private String filePath;
    private String folderPath;

    /**
     * Constructs a new Storage instance with the file path for data storage.
     *
     * @param filePath The file path to load and save task data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        this.folderPath = file.getAbsoluteFile().getParent();
    }

    /**
     * Loads and retrieves a list of tasks from the specified file.
     *
     * @return A List containing the tasks loaded from the file.
     * @throws DukeException If an error occurs while loading tasks or creating the file.
     */
    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        try {
            File resourceFile = new File(filePath);
            Scanner myReader = new Scanner(resourceFile);
            int count = -1;
            while (myReader.hasNextLine()) {
                count += 1;
                String data = myReader.nextLine();
                String[] parts = data.split(" \\| ");
                String taskType = parts[0].substring(1, 2);
                String description = parts[2];
                String date = parts.length > 3 ? parts[3] : "";
                if (taskType.equals("T")) {
                    Task currTask = new ToDo(description);
                    tasks.add(currTask);
                    if (parts[1].charAt(1) == 'X') {
                        tasks.get(count).markAsDone();
                    }
                } else if (taskType.equals("E")) {
                    String start = date.split("-")[0] + "pm";
                    String end = date.split("-")[1].trim();
                    Task currTask = new Event(description, start, end);
                    tasks.add(currTask);
                    if (parts[1].charAt(1) == 'X') {
                        tasks.get(count).markAsDone();
                    }
                } else if (taskType.equals("D")) {
                    Task currTask = new Deadline(description, date);
                    tasks.add(currTask);
                    if (parts[1].charAt(1) == 'X') {
                        tasks.get(count).markAsDone();
                    }
                }
            }
            myReader.close();
            return tasks;
        } catch (FileNotFoundException e) {
            createDataFolderIfNeeded(folderPath);
            createDataFileIfNeeded(filePath);
            throw new DukeException("Unable to load file. Created an empty file!");
        }
    }

    /**
     * Creates a data folder if it doesn't exist at the specified folder path.
     *
     * @param folderPath The folder path where the data folder should be created.
     */
    private static void createDataFolderIfNeeded(String folderPath) {
        File dataFolder = new File(folderPath);
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
    }

    /**
     * Creates a data file if it doesn't exist at the specified file path.
     *
     * @param filePath The file path where the data file should be created.
     */
    private static void createDataFileIfNeeded(String filePath) {
        File dataFile = new File(filePath);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
