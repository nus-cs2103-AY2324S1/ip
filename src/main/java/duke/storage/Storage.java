package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.parser.Parser;
import duke.tasks.TaskList;



/**
 * Manages the storage of tasks in a txt file.
 */
public class Storage {
    protected String filePath;
    protected String directoryPath;

    /**
     * Initializes a new instance of the duke.storage.Storage class.
     *
     * @param directoryPath The directory where the storage file is located or should be created.
     * @param filePath The file path of the storage file.
     */
    public Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
        this.setupTasks();
    }


    /**
     * Updates the storage file with the tasks from the provided duke.tasks.TaskList.
     *
     * @param tasks The duke.tasks.TaskList containing tasks to be saved to the storage file.
     */
    public void updateStorage(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < tasks.getSize(); i++) {
                writer.write(tasks.getTask(i) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    /**
     * Sets up the tasks by reading from the storage file or creating a new one if it doesn't exist.
     *
     * @return A duke.tasks.TaskList containing the tasks read from the storage file, or an empty
     *     duke.tasks.TaskList if the file is new.
     */
    private TaskList setupTasks() {
        TaskList tasks = new TaskList();
        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File taskFile = new File(filePath);
            if (!taskFile.createNewFile()) {
                tasks = getTasks();
            }
        } catch (IOException e) {
            System.out.println("Error occurred");
            e.printStackTrace();
        } finally {
            return tasks;
        }
    }

    /**
     * Retrieves tasks from the storage file and returns them in a duke.tasks.TaskList.
     *
     * @return A duke.tasks.TaskList containing tasks read from the storage file.
     */
    public TaskList getTasks() {
        TaskList taskList = new TaskList();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String storageText = scanner.nextLine();
                char taskType = storageText.charAt(1);
                switch (taskType) {
                case 'T':
                    taskList.addTask(Parser.parseStorageToToDo(storageText));
                    break;
                case 'D':
                    taskList.addTask(Parser.parseStorageToDeadline(storageText));
                    break;
                case 'E':
                    taskList.addTask(Parser.parseStorageToEvent(storageText));
                    break;
                default:
                    break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            return taskList;
        }
    }
}
