package chatbuddy.storage;

import chatbuddy.ChatBuddyException;
import chatbuddy.parser.Parser;
import chatbuddy.TaskList;
import chatbuddy.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage represents a class to handle operations relating to the local storage of task data.
 * Data from TaskList can be saved into or loaded from the local storage.
 */
public class Storage {
    /** The filepath of the file containing the task data. */
    private String filePath;

    /**
     * Creates an instance of a Storage object with the given filepath.
     *
     * @param filePath The filepath of the file containing the task data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task data from the file.
     *
     * @return A list of tasks.
     * @throws ChatBuddyException If there is an error creating or finding the file.
     */
    public ArrayList<Task> load() throws ChatBuddyException {
        // load file from hard disk
        File file = new File(this.filePath);
        File parentDirectory = file.getParentFile();

        // check for existence of parentDirectory and file
        if (!parentDirectory.exists()) {
            parentDirectory.mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new ChatBuddyException("Error creating data file.");
            }
        }

        // load data from file
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                // populate task array
                Task task = Parser.parseToTask(fileScanner.nextLine());
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new ChatBuddyException("Data file not found.");
        }

        return taskList;
    }

    /**
     * Saves the task data into the file.
     * Date in the file is overwritten.
     *
     * @param tasks The list of tasks to save.
     * @throws ChatBuddyException If there is an error saving data into the file.
     */
    public void save(TaskList tasks) throws ChatBuddyException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            ArrayList<String> taskStrings = tasks.getTaskStringsToSave();
            for (String taskString : taskStrings) {
                fileWriter.write(taskString + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new ChatBuddyException("Error saving data into file.");
        }

    }
}
