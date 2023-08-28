import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.List;
import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class TaskFile {
    final static String fileName = "./data/duke.txt";
    public static void saveTask(List<Task> tasks) {
        Path path = Paths.get(fileName);
        try {
            if (!Files.exists(path)) {
                Path dirPath = Paths.get("./data");
                Files.createDirectories(dirPath);
                File file = new File(fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                    String from = ((Event) task).from;
                    String to = ((Event) task).to;
                    text = "E|" + done + "|" + desc + "|" + from + "|" + to;
                } else if (task instanceof Deadline) {
                    String done = task.isDone
                            ? "1"
                            : "0";
                    String desc = task.description;
                    String by = ((Deadline) task).by;
                    text = "D|" + done + "|" + desc + "|" + by;
                }
                fileWriter.write(text + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Task> loadTasks() throws DukeException{
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNextLine()) {
                tasks.add(Task.parse(sc.nextLine()));
            }
            return tasks;
        } catch (IOException e) {
            return null;
        }
    }
}