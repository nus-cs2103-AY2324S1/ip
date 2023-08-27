import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.List;


class TaskFile {
    public static void saveTask(Task task, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(task);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Task> loadTasks(String fileName) {
        List<Task> tasks = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            tasks = (List<Task>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}