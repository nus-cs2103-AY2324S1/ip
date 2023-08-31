package zean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import zean.exception.DukeException;
import zean.task.Task;

/**
 * The class that deals with loading tasks from the file and saving tasks in the file.
 *
 * @author Zhong Han
 */
public class Storage {

    private File f;

    private String filePath;

    /**
     * Empty constructor for storage.
     * For the purpose of testing.
     */
    public Storage() {

    }

    /**
     * Constructor for storage.
     * Creates the necessary directory and file if not present.
     * Reads the present content in the file into the ArrayList.
     */
    public Storage(String filePath) throws FileNotFoundException, IOException {
        this.filePath = filePath;

        File dir = new File("./data");
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                throw new DukeException("\tOOPS! The file cannot be created.");
            }
        }
        File f = new File(this.filePath);
        this.f = f;
        if (!f.exists()) {
            f.createNewFile();
            System.out.println("\tA new folder and file to store your tasks has been created.");
        }
    }

    /**
     * Returns an ArrayList populated with tasks that is present in the file.
     *
     * @return An ArrayList populated with tasks.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = null;
        try {
            sc = new Scanner(this.f);
            while (sc.hasNext()) {
                Parser.parseToTask(tasks, sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("\tOOPS! The file cannot be created.");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return tasks;
    }

    /**
     * Appends task to the file.
     *
     * @param task The task to be written to the file.
     */
    public void addToDisk(Task task) {
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.write(task.toStringForFile() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("\tOOPS! The file is not available!");
        }
    }

    /**
     * Overwrites all the tasks to the file.
     *
     * @param tasks The ArrayList containing all the tasks to be written to the file.
     */
    public void rewriteToDisk(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toStringForFile() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("\tOOPS! The file is not available!");
        }
    }
}
