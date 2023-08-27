import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    Task task = Task.createTaskFromFormattedString(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                } else {
                    break;
                }
            }
            scanner.close();
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from file.");
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws DukeException {
        try {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(task.toFormattedString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error saving tasks to file.");
        }
    }
}
