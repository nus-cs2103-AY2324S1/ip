package duke;

import duke.exceptions.InvalidFileTypeException;

import java.io.*;

import java.util.ArrayList;
import duke.task.*;

/**
 * Used for any reading and writing to files for the chatbot
 */
public class Storage {
    protected String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from a given file to an arrayList
     * @return a ArrayList of Task objects that were loaded from the this.filePath path
     * @throws InvalidFileTypeException
     */
    public ArrayList<Task> load() throws InvalidFileTypeException {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        String line;
        Parser parser = new Parser();

        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            while ((line = reader.readLine()) != null) {
                taskArrayList.add(parser.parseSave(line));
            }
            return taskArrayList;
        } catch (IOException e) {
            throw new InvalidFileTypeException("Error: File not found");
        }
    }

    /**
     * Saves the current tasks from the taskList to the given file
     * @param taskList an object containing the List of tasks to be stored
     */
    public void save(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath))) {
            writer.write(taskList.toTaskSave());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
