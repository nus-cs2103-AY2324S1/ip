package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a storage that stores the list of tasks.
 */
public class Storage {

    /** Represents the path of the file that stores the list of tasks. */
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     * 
     * @param filePath The path of the file that stores the list of tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.print(Ui.returnErrorString(new DukeException(e.getMessage())));
            }
        }
    }

    /**
     * Saves the list of tasks to the file.
     * 
     * @param tasks The list of tasks.
     */
    public void saveTask(ArrayList<Task> tasks) {
        try {
            FileOutputStream fos = new FileOutputStream(this.filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.print(Ui.returnErrorString(new DukeException(e.getMessage())));
        }
    }

    /**
     * Loads the list of tasks from the file.
     * 
     * @return The method is returning the list of tasks.
     */
    public ArrayList<Task> loadTask() {
        try {
            FileInputStream fis = new FileInputStream(this.filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            // Object is of type ArrayList<Task> hence it is possible to case directly
            @SuppressWarnings("unchecked")
            ArrayList<Task> tasks = (ArrayList<Task>) ois.readObject();
            ois.close();
            fis.close();
            
            return tasks;
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<Task>();
        }
    } 
}
