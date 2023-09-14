package zean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import zean.exception.ZeanException;
import zean.task.Task;

/**
 * The class that deals with loading tasks from the file and saving tasks in the file.
 *
 * @author Zhong Han
 */
public class Storage {

    private File file;

    private String filePath;

    private String createFileMsg = "";

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
     *
     * @param filePath The filepath of the text file to be read from and written to.
     * @throws FileNotFoundException If the provided filepath is invalid.
     * @throws IOException If an I/O error occurred.
     */
    public Storage(String filePath) throws FileNotFoundException, IOException {
        this.filePath = filePath;

        File dir = new File("./data");
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                throw new ZeanException("OOPS! The file cannot be created.");
            }
        }
        File file = new File(this.filePath);
        this.file = file;
        if (!file.exists()) {
            file.createNewFile();

            // To be displayed on the GUI to inform users
            this.createFileMsg = "A new folder and file to store your tasks has been created.";
        }
    }

    public String getCreateFileMsg() {
        return this.createFileMsg;
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
            sc = new Scanner(this.file);
            while (sc.hasNext()) {
                Parser.parseToTask(tasks, sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new ZeanException("OOPS! The file cannot be created.");
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
            throw new ZeanException("OOPS! The file is not available!");
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
            throw new ZeanException("OOPS! The file is not available!");
        }
    }
}
