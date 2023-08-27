import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private File folder;
    private File file;
    private final String PATH = System.getProperty("user.dir");

    public Storage() {
        this.file = new File(PATH + "\\data\\duke.txt");
        this.folder = new File(PATH + "\\data");
        // Directory doesn't exist
        if (!this.directoryExists()) {
            this.createDirectory();
        }
        // File doesn't exist
        if (!this.fileExists()) {
            this.createFile();
        }
    }

    void createDirectory() {
        folder.mkdir();
    }

    void createFile() {
        try {
            this.file.createNewFile();
        } catch (IOException err) {
            System.out.println(err);
        }
    }

    boolean directoryExists() {
        java.nio.file.Path path = java.nio.file.Paths.get(PATH, "data");
        return java.nio.file.Files.exists(path);
    }

    boolean fileExists() {
        return this.file.exists();
    }

    ArrayList<Task> readData() {
        ArrayList<Task> data = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNext()) {
                Task task = this.stringToTask(scanner.nextLine());
                if (task != null) {
                    data.add(task);
                }
            }
        } catch (FileNotFoundException exc) {
            System.out.println(exc);
        }
        return data;
    }

    void writeData(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(this.file);
            for (Task task: tasks) {
                writer.write(task.toFile() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException exc) {
            System.out.println(exc);
        }
    }

    Task stringToTask(String line) {
        String[] split = line.split(" \\| ", 4);

        // Corrupted File
        if (split.length < 3) {
            System.out.println("Error!");
            return null;
        }

        String type = split[0];
        String status = split[1];
        String action = split[2];

        Task task;

        switch(split[0]) {
            case "T":
                task = new Todo(action, status);
                break;
            case "D":
                task = new Deadline(action, split[3], status);
                break;
            case "E":
                String[] interval = split[3].split("-", 2);
                if (interval.length < 2) {
                    task = null;
                } else {
                    task = new Event(action, interval[0], interval[1], status);
                }
                break;
            default:
                task = null;
        }

        return task;
    }

}
