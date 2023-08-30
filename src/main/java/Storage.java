import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        File f = new File(this.filePath);
        if (!f.exists()) f.createNewFile();

        Scanner sc = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            Task task;
            String[] taskDetails = sc.nextLine().split(" , ");
            switch (taskDetails[0]) {
                case "T":
                    task = new Todo(taskDetails[2], taskDetails[1].equals("1"));
                    break;
                case "D":
                    task = new Deadline(taskDetails[2], taskDetails[3], taskDetails[1].equals("1"));
                    break;
                case "E":
                    task = new Event(taskDetails[2], taskDetails[3], taskDetails[4], taskDetails[1].equals("1"));
                    break;
                default:
                    task = null;
                    break;
            }
            tasks.add(task);
        }
        return tasks;
    }

    public void append(Task task) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);
        fw.write(task.getTaskFileString() + System.lineSeparator());
        fw.close();
    }

    public void update(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        tasks.forEach(task -> {
            try {
                fw.write(task.getTaskFileString() + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        fw.close();
    }
}
