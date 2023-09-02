import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Storage {
    static final String FILE_NAME = "./data/duke.txt";

    static void checkFileExists() {
        Path path = Paths.get(FILE_NAME);
        try {
            if (!Files.exists(path)) {
                Ui.print("data file not found, creating a new one");
                Path dirPath = Paths.get("./data");
                Files.createDirectories(dirPath);
                File file = new File(FILE_NAME);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveTask(List<Task> tasks) {
        checkFileExists();
        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            for (Task task : tasks) {
                String text = "";
                if (task instanceof Todo) {
                    String done = task.isDone
                            ? "1"
                            : "0";
                    String desc = task.description;
                    text = "T|" + done + "|" + desc;
                } else if (task instanceof Event) {
                    String done = task.isDone
                            ? "1"
                            : "0";
                    String desc = task.description;
                    LocalDate from = ((Event) task).from;
                    LocalDate to = ((Event) task).to;
                    text = "E|" + done + "|" + desc + "|" + from + "|" + to;
                } else if (task instanceof Deadline) {
                    String done = task.isDone
                            ? "1"
                            : "0";
                    String desc = task.description;
                    LocalDate by = ((Deadline) task).by;
                    text = "D|" + done + "|" + desc + "|" + by;
                }
                fileWriter.write(text + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Task> loadTasks() throws DukeException {
        checkFileExists();
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(FILE_NAME));
            while (sc.hasNextLine()) {
                String next = sc.nextLine();
                Task nextTask = Task.parse(next);
                tasks.add(nextTask);
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException("There are no tasks");
        }
    }
}
