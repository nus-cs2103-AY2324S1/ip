import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String path;
    File file;

    public Storage() {
        this.path = "./data/duke.txt";
        this.file = new File(this.path);
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter filewriter = new FileWriter(this.path);
        for (Task task : tasks) {
            filewriter.write(task.toSaveString() + "\n");
        }
        filewriter.close();
    }

    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();

        File file = new File(this.path);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Task task = Task.parseTask(line);
            tasks.add(task);
        }
        return tasks;
    }
}
