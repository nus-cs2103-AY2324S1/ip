package catbot.task;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.function.Supplier;

import catbot.internal.ObjectStorage;

/**
 * Dedicated class to read and write ArrayLists of Tasks from storage.
 */
public class TaskArrayListStorage implements ObjectStorage<ArrayList<Task>> {

    private final String path;
    private Supplier<ArrayList<Task>> supplier;

    /**
     * Constructs a TaskArrayListStorage with a path to read from and write to.
     *
     * @param path String representing relative directory to read and write.
     */
    public TaskArrayListStorage(String path) {
        this.path = path;
    }

    @Override
    public void write(ArrayList<Task> taskArrayList) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path));
            output.writeObject(taskArrayList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Task> readOrDefault() {
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(path));
            Object readObject = input.readObject();
            @SuppressWarnings("unchecked")
            ArrayList<Task> tasks = (ArrayList<Task>) readObject;
            input.close();
            return tasks;
        } catch (IOException | ClassNotFoundException ignored) {
            if (supplier != null) {
                return supplier.get();
            } else {
                return null;
            }
        }
    }

    @Override
    public void setDefault(Supplier<ArrayList<Task>> supplier) {
        this.supplier = supplier;
    }
}
