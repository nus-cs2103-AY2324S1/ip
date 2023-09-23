package adam;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import adam.exception.NoFileException;
import adam.tasks.Task;

/**
 * This class is used to store and read the array of tasks inside the hard disk.
 */
public class Storage {

    private String home = System.getProperty("user.home");
    private java.nio.file.Path path = java.nio.file.Paths.get(home, "Digimon.txt");

    /**
     * Returns an Arraylist of tasks object that is used to save tasks.
     *
     * @return ArrayList object of type Task.
     */
    public ArrayList<Task> read() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path.toFile()));
            ArrayList<Task> array = (ArrayList<Task>) in.readObject();
            in.close();
            return array;
        } catch (IOException e) {
            throw new NoFileException();
        } catch (ClassNotFoundException e) {
            throw new NoFileException();
        }
    }

    /**
     * Saves an ArrayList into a file inside the hard disk.
     *
     * @param tasks ArrayList Object that is going to be saved.
     */

    public void write(ArrayList<Task> tasks) {
        try {
            ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream(path.toFile()));
            ob.writeObject(tasks);
            ob.flush();
            ob.close();
        } catch (IOException e) {
            throw new NoFileException();
        }
    }
}
