package monday.storage;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.EOFException;
import java.util.ArrayList;

import monday.task.Task;

public class Storage {
    private final String FILEPATH;

    public Storage(String fileName) {
        this.filePath = fileName;

        File file = new File(fileName);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }

    public void save(ArrayList<Task> tasks) throws IOException{
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filePath))){
            output.writeObject(tasks);
        }
    }

    public ArrayList<Task> load() throws ClassNotFoundException, IOException{

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath))){
            // We know the objects inside are all Tasks,therefore we can suppress the unchecked warning.
            @SuppressWarnings("unchecked")
            ArrayList<Task> tasklist = (ArrayList<Task>) input.readObject();
            return tasklist;
        } catch (EOFException e) {
            return new ArrayList<>();
        }
    }
}
