package monke;

import monke.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles reading and writing data to a specified file.
 */
public class Storage {
    /** The file path where task data is stored */
    private String filepath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filepath The path to the file where task data is stored.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads task data from the file and returns a list of tasks.
     *
     * @return An ArrayList object containing the loaded tasks.
     * @throws MonkeException If the file cannot be found.
     */
    public ArrayList<Task> load() throws MonkeException {
        try {
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();
            while (scanner.hasNext()) {
                Task task = Parser.parseLoadedData(scanner.nextLine());
                tasks.add(task);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new MonkeException("Could not load tasks");
        }
    }

    /**
     * Saves task data to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws MonkeException If the file cannot be opened.
     */
    public void saveData(TaskList tasks) throws MonkeException {
        try {
            FileWriter fileWriter = new FileWriter(filepath);
            StringBuilder textToAdd = new StringBuilder();
            for (Task task : tasks.toList()) {
                textToAdd.append(task.formatData());
            }
            fileWriter.write(textToAdd.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new MonkeException("Could not open file");
        }
    }
}