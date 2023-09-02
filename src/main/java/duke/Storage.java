package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class to handle all the loading and saving of tasks from the file
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load the list of tasks from the storage file
     *
     * @return the array list containing all the task from the file
     * @throws DukeException if file is not found or there is IO excpetion
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                if (f.createNewFile()) {
                    System.out.println("File created: " + f.getName());
                }
            }

            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.trim().isEmpty()) {
                    Task task = Task.fromString(line);
                    tasks.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found: " + e.getMessage());
        } catch (IOException e) {
            throw new DukeException("Error creating file" + e.getMessage());
        }
        return tasks;
    }

    /**
     * Save the list of tasks to the storage file
     *
     * @param list the list of task that has been edited
     * @throws DukeException if there is error saving to the file
     */
    public void saveTasksToFile(ArrayList<Task> list) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : list) {
                fw.write(task.save());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error saving tasks to file: " + e.getMessage());
        }
    }
}

