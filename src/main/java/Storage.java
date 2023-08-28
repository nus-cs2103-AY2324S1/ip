import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class that provides the storage for the tasks.
 *
 * @author Zhong Han
 */
public class Storage {
    private ArrayList<Task> tasks;
    private int count;

    private File f;

    private String filePath;

    /**
     * Constructor for storage.
     * Creates the necessary directory and file if not present.
     * Reads the present content in the file into the ArrayList.
     */
    public Storage(String filePath) throws FileNotFoundException, IOException {
        this.tasks = new ArrayList<>();
        this.count = 0;
        this.filePath  = filePath;

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

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = null;
        try {
            sc = new Scanner(this.f);
            while (sc.hasNext()) {
                this.count = Parser.parseToTask(tasks, sc.nextLine());
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

    public void addToDisk(Task task) {
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.write(task.toStringForFile() + System.lineSeparator());
            this.count++;
            fw.close();
        } catch (IOException e) {
            throw new DukeException("\tOOPS! The file is not available!");
        }
    }

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
