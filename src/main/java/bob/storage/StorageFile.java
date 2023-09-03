package bob.storage;

import bob.exception.BobException;
import bob.parser.Parser;
import bob.task.Task;
import bob.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles reading and writing save files from the system.
 */
public class StorageFile {
    private String fileDirectoryPath;
    private String fileName;

    /**
     * Constructor of the StorageFile class.
     *
     * @param fileDirectoryPath Relative path to directory containing save file
     * @param fileName Name of save file
     */
    public StorageFile(String fileDirectoryPath, String fileName) {
        this.fileDirectoryPath = fileDirectoryPath;
        this.fileName = fileName;
    }

    /**
     * Saves the current state of tasks by writing into a .txt file based on relative
     * file path determined when current object instantiated.
     *
     * @param taskList List of tasks to be written into save file
     * @throws BobException If there is error when accessing current state of task list
     */
    public void saveTasks(TaskList taskList) throws BobException {
        this.checkDirectoryExists();
        try {
            FileWriter fileWriter = new FileWriter(this.fileDirectoryPath + this.fileName);
            for (int i = 0; i < taskList.size(); i++) {
                fileWriter.write(taskList.getTask(i).convertToFileFormat() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new BobException("Something went wrong when saving your tasks :(");
        }
    }

    /**
     * Reads from save file and changes current memory state of application, effective loading
     * tasks stored in previous sessions.
     *
     * @return A TaskList of previously saved tasks
     * @throws BobException if save file has lines with incorrect format
     */
    public TaskList loadTasks() throws BobException {
        this.checkDirectoryExists();
        try {
            File taskFile = new File(this.fileDirectoryPath + this.fileName);
            Scanner fileScanner = new Scanner(taskFile);
            ArrayList<Task> loadedTasks = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                Task task = Parser.parseStoredTask(fileScanner.nextLine());
                loadedTasks.add(task);
            }
            fileScanner.close();
            return new TaskList(loadedTasks);
        } catch (FileNotFoundException e) {
            return new TaskList();
        }
    }

    private void checkDirectoryExists() throws BobException {
        File dataDirectory = new File(this.fileDirectoryPath);
        if (!dataDirectory.exists()) {
            boolean isMkDirSuccess = dataDirectory.mkdir();
            if (!isMkDirSuccess) {
                throw new BobException("Something went wrong when loading saving/loading your tasks :(");
            }
        }
    }
}
