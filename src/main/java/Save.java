import java.util.ArrayList;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Save {
    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/duke.txt";

    public void saveTasks(ArrayList<Task> tasks) {
        try {
            File directory = new File(DIRECTORY_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            } try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (Task task : tasks) {
                    writer.write(task.format());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Well, the bad news is that the Tasks could not be saved: " + e.getMessage());
        }
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return tasks;
            } try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = parseLineToTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Well, the bad news is that the Tasks could not be saved: " + e.getMessage());
        } return tasks;
    }

    private static Task parseLineToTask(String line) {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
            case "T":
                Todo todo = new Todo(parts[2]);
                if (parts[1].equals("1")) todo.markAsDone();
                return todo;
            case "D":
                Deadline deadline = new Deadline(parts[2], parts[3]);
                if (parts[1].equals("1")) deadline.markAsDone();
                return deadline;
            case "E":
                String[] timeParts = parts[3].split("-");
                Event event = new Event(parts[2], timeParts[0].trim(), timeParts[1].trim());
                if (parts[1].equals("1")) event.markAsDone();
                return event;
            default:
                return null;
        }
    }
}