import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This is the class used to load and store data into the data.s file.
 */
public class DataSaver {
    //The path directory of test.s
    private String path;

    /**
     * Returns an instance of DataSaver.
     * @param path the directory of test.s.
     */
    public DataSaver(String path) throws IOException {
        this.path = path;

        File file = new File(path);
        File parent = file.getParentFile();

        if (!parent.exists()) {
            parent.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Stores all tasks into the test.h file.
     * @param tasks the list of all tasks.
     */
    public void store(ArrayList<Task> tasks) {
        ObjectOutputStream outStore = null;
        try {
            FileOutputStream fileOut = new FileOutputStream(path);

            outStore = new ObjectOutputStream(fileOut);

            outStore.writeObject(tasks);

        } catch (IOException e) {
            System.out.println("Error loading file! " + e.getMessage());
        } finally {
            try {
                if (outStore != null) {
                    outStore.close();
                }
            } catch (IOException e) {
                System.out.println("Error loading file! " + e.getMessage());
            }

        }
    }

    /**
     * Loads all tasks from the data.s file.
     * @return The list of tasks found in the data.s file.
     */
    @SuppressWarnings("unchecked")
    // Suppresses unchecked cast warning because we know the deserialized object is an ArrayList<Task>.
    public ArrayList<Task> load() throws IOException, ClassNotFoundException {
        ArrayList<Task> allTasks = new ArrayList<>();
        ObjectInputStream inputStream = null;
        try {
            FileInputStream fileInput = new FileInputStream(path);

            inputStream = new ObjectInputStream(fileInput);

            allTasks = (ArrayList<Task>) inputStream.readObject();
        } catch (EOFException e) {
            //The error indicates a lack of tasks available, so we can just return allTasks as an empty list at the end
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }

            return allTasks;
        }
    }


}
