import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Responsible for loading saved task list from/writing updated task list to plaintext file.
 * FORMAT: [Type]/[isDone]/[description]/[time]
 *
 */
public class Storage {
    private String filePath;
    private File file = null;
    public Storage(String filePath) {
        this.filePath = filePath;


    }

    /**
     * Loads task list from file.
     *
     * @return A list of saved tasks.
     * @throws FileNotFoundException
     */
    public ArrayList<Task> load() throws FileNotFoundException{
        this.file = new File(filePath);
        Scanner sc = new Scanner(file);
        ArrayList<Task> result = new ArrayList<>();
        while (sc.hasNext()) {
            result.add(getTaskFromString(sc.nextLine()));
        }
        return result;
    }

    /**
     * Parses plaintext and converts it into a Task.
     *
     * @param str
     * @return The converted Task.
     */
    private Task getTaskFromString(String str) {
        return null;
    }

    /**
     * Converts the Task into a correctly formatted string for storage.
     *
     * @param task
     * @return
     */
    private String getStringFromTask(Task task) {
        return null;
    }
}
