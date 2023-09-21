package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;



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
            Scanner fileReader = new Scanner(resourceFile);
            int count = -1;
            while (fileReader.hasNextLine()) {
                count += 1;
                String data = fileReader.nextLine();
                String[] parts = data.split(" \\| ");
                String taskType = parts[0].substring(1, 2);
                String description = parts[2];
                String date = parts.length > 3 ? parts[3] : "";
                if (taskType.equals("T")) {
                    Task currTask = new ToDo(description);
                    tasks.add(currTask);
                } else if (taskType.equals("E")) {
                    String start = date.split("-")[0] + "pm";
                    String end = date.split("-")[1].trim();
                    Task currTask = new Event(description, start, end);
                    tasks.add(currTask);
                } else if (taskType.equals("D")) {
                    Task currTask = new Deadline(description, date);
                    tasks.add(currTask);
                }
                if (parts[1].charAt(1) == 'X') {
                    tasks.get(count).markAsDone();
                }
            }
            fileReader.close();
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
    /**
     * Writes the contents of a TaskList to a file specified by the current instance's file path.
     * Each task is written as a separate line in the file.
     *
     * @param taskList The TaskList containing tasks to be written to the file.
     * @throws DukeException If an IOException occurs while writing to the file.
     */
    public void writeLine(TaskList taskList) throws DukeException {
        File resourceFile = new File(this.filePath);
        try {
            FileWriter writer = new FileWriter(resourceFile);
            for (Task task : taskList.getTasks()) {
                writer.write(task.toTxtString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }
}
