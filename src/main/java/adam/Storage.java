package adam;

import java.io.*;
import java.util.ArrayList;
import adam.tasks.Task;
import adam.exception.AdamException;
public class Storage {
    String home = System.getProperty("user.home");
    java.nio.file.Path path = java.nio.file.Paths.get(home, "Pokemon.txt");

    boolean directoryExists = java.nio.file.Files.exists(path);
    public ArrayList<Task> read() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path.toFile()));
            ArrayList<Task> array = (ArrayList<Task>) in.readObject();
            in.close();
            return array;
        } catch (IOException e) {
            throw new AdamException();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void write(ArrayList<Task> array) {
        try {
            ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream(path.toFile()));
            ob.writeObject(array);
            ob.flush();
            ob.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
