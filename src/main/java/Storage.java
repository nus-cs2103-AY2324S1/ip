import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        // Create data folder and file if they don't exist
        File file = new File(filePath);
        File folder = file.getParentFile();

        if (!folder.exists()) {
            folder.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        this.filePath = filePath;
    }
    public List<Task> load() throws InvalidArgumentException{
        List<Task> list = new ArrayList<>();
        File file = new File(filePath);
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                Task task = parseFromFile(line);
                list.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public void save(TaskList list) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : list) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to disk.");
        }
    }
    private static Task parseFromFile(String line) throws InvalidArgumentException{
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;

        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                String deadline = parts[3];
                task = new Deadline(description, deadline);
                break;
            case "E":
                String eventTime = parts[3];
                int toIndex = eventTime.indexOf("to");
                String from = eventTime.substring(0, toIndex).trim();
                String to = eventTime.substring(toIndex + 2).trim();
                task = new Event(description, from, to);
                break;
            default:
                throw new InvalidArgumentException();
        }

        if (isDone) {
            task.markDone();
        }

        return task;
    }
}
