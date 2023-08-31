package duke;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

/**
 * Storage class handles loading tasks from file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error while creating file: " + e.getMessage());
            }
        }
    }

    /**
     * Loads tasks from the file.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
    public ArrayList<Task> load() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            in = new ObjectInputStream(fileIn);
            // Suppress warning as deserialised object is of ArrayList<duke.Task>
            @SuppressWarnings("unchecked")
            ArrayList<Task> list = (ArrayList<Task>) in.readObject();
            return list;
        } catch (EOFException e) {
            return new ArrayList<Task>();
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     * Saves tasks to the file.
     *
     * @param list The ArrayList of Task objects to be saved to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveToFile(ArrayList<Task> list) throws IOException{
        ObjectOutputStream out = null;
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
