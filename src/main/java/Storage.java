import Exceptions.DukeException;
import Exceptions.NoTaskFoundException;
import Tasks.Task;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    String filepath;
    public Storage(String filepath) {
        this.filepath = filepath;
    }


    public List<Task> load() throws DukeException {
        System.out.println("Loading tasks...");
        List<Task> tasks = new ArrayList<Task>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Parser.parseTaskFromFileString(line);
                tasks.add(task);
            }
            System.out.println("Tasks loaded successfully!");
            return tasks;
        } catch (IOException e) {
            System.out.println("Tasks.Task failed to load");
            throw new NoTaskFoundException();
        }
    }
}
