import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class TaskFile {
    final static String fileName = "./data/duke.txt";

    static void checkFileExists() {
        Path path = Paths.get(fileName);
        try {
            if (!Files.exists(path)) {
                System.out.println("data file not found, creating a new one");
                Path dirPath = Paths.get("./data");
                Files.createDirectories(dirPath);
                File file = new File(fileName);
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveTask(List<Task> tasks) {
        checkFileExists();
        try (FileWriter fileWriter = new FileWriter(fileName)) {
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


    public static ArrayList<Task> loadTasks() throws DukeException{
        checkFileExists();
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNextLine()) {
                tasks.add(Task.parse(sc.nextLine()));
            }
            return tasks;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new DukeException("There are no tasks");
        }
    }
}