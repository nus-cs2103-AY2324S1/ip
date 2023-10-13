package fluke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import fluke.exceptions.FlukeException;
import fluke.tasks.Task;

/**
 * Handles storing of task list data.
 */
public class Storage {
    private final String filePath;
    private final ArrayList<Task> tasksReadFromStorage;

    /**
     * Constructs a Storage with an appropriate filePath.
     * @param filePath file path of the text file to store task data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasksReadFromStorage = new ArrayList<>();
    }

    /**
     * Attempts to load tasks from local storage.
     * @return An ArrayList containing tasks.
     */
    public ArrayList<Task> load() throws FlukeException {
        File saveFile = new File(filePath);
        if (saveFile.exists()) {
            // parse the file and write to list
            try {
                readFile(saveFile);
            } catch (FileNotFoundException f) {
                throw new FlukeException(f.getMessage());
            }
            return tasksReadFromStorage;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Helper function to read file
     * @param saveFile the save file to read
     * @throws FileNotFoundException if file cannot be found
     * @throws FlukeException if there is an error parsing the file
     */
    private void readFile(File saveFile) throws FileNotFoundException, FlukeException {
        Scanner fileScanner = new Scanner(saveFile);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            if (!line.equals("")) {
                Task task = Parser.parseTask(line);
                tasksReadFromStorage.add(task);
            }
        }
    }

    /**
     * Saves list of tasks to local storage.
     * @param tasks An ArrayList containing tasks to be saved.
     * @throws IOException Error that occurs during saving
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        System.out.println(tasks);
        FileWriter writer = new FileWriter(this.filePath);
        for (int i = 0; i < tasks.size(); i++) {
            String out = tasks.get(i).toString() + "\n";
            writer.write(out);
        }
        writer.close();
    }
}
