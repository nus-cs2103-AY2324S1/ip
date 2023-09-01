import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public ArrayList<Task> load() throws DukeException, ClassNotFoundException, IOException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath));
        ArrayList<Task> loadedTasks = (ArrayList<Task>) inputStream.readObject();
        if (loadedTasks.isEmpty()) {
            throw new DukeException("currently there is no task in your Tasks");
        }
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.addAll(loadedTasks);
        return tasks;
    }
}
