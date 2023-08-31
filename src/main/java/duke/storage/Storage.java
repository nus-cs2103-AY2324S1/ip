package duke.storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import duke.DukeException;
import duke.task.*;

/**
 * Represents a class for managing data storage and retrieval.
 */
public class Storage {
    private final String filepath;
    private static final String LINE = "___________________________________\n";

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filepath The path to the file where data will be stored.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return An ArrayList containing the loaded tasks.
     * @throws DukeException If there is an issue loading tasks from the file.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> temp = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(this.filepath))) {
            while (fileScanner.hasNextLine()) {
                String taskString = fileScanner.nextLine();
                String[] arr = taskString.split("\\|");
                Task.readListFromFile(arr, temp);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Looks like this is your first time!\n" +
                    "Let's start with a new list!\n" + LINE);
        }
        return temp;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param list The ArrayList of tasks to be saved.
     */
    public void saveDataToFile(ArrayList<Task> list) {
        File folder = new File("./data/");
        if (!folder.exists()) folder.mkdirs();
        try (PrintWriter writer = new PrintWriter(this.filepath)) {
            for (Task task : list) {
                writer.println(task.toStringFile());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
