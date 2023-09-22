package chatbuddy.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import chatbuddy.ChatBuddyException;
import chatbuddy.TaskList;
import chatbuddy.parser.Parser;
import chatbuddy.task.Task;

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
        File file = getFile();
        ArrayList<Task> taskList = getTasks(file);
        return taskList;
    }

    /**
     * Returns file based on filePath.
     *
     * @return The file with the given filePath.
     * @throws ChatBuddyException If there is an error creating the file if the file does not exist.
     */
    private File getFile() throws ChatBuddyException {
        File file = new File(this.filePath);
        File parentDirectory = file.getParentFile();

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

        return file;
    }

    /**
     * Returns a list of tasks based on the data in the file.
     *
     * @param file The file to read the data from.
     * @return A list of tasks based on the data in the file.
     * @throws ChatBuddyException If the file is not found.
     */
    private ArrayList<Task> getTasks(File file) throws ChatBuddyException {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
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
            writeListToFile(fileWriter, taskStrings);
            fileWriter.close();
        } catch (IOException e) {
            throw new ChatBuddyException("Error saving data into file.");
        }

    }

    /**
     * Adds list of strings to a file.
     *
     * @param fileWriter The filewriter used to write the strings into the file.
     * @param taskStrings The list of strings to add to the file.
     * @throws IOException If there is an error writing the string to the file.
     */
    private void writeListToFile(FileWriter fileWriter, ArrayList<String> taskStrings) throws IOException {
        for (String taskString : taskStrings) {
            fileWriter.write(taskString + System.lineSeparator());
        }
    }
}
