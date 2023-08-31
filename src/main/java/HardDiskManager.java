import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class HardDiskManager {
    private final String filePath;
    private final File hardDiskFile;

    public HardDiskManager(String filePath) {
        this.filePath = filePath;
        this.hardDiskFile = new File(filePath);
    }

    private void createFile() {
        try {
            File parentDir = hardDiskFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            hardDiskFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error when creating new file: " + e);
        }
    }

    public ArrayList<Task> getTasks() {
        createFile();

        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length < 3) {
                    continue;
                }

                String identifier = parts[0];
                boolean done = parts[1] == "1";
                String name = parts[2];

                if (identifier.equals("T") && parts.length == 3) {
                    tasks.add(new Todo(name, done));
                } else if (identifier.equals("D") && parts.length == 4) {
                    LocalDateTime dateTime = null;
                    String[] dSplitStr = parts[3].split(" ");
                    if (dSplitStr.length == 2) {
                        dateTime = DateManager.parseStorageDateString(dSplitStr[0], dSplitStr[1]);
                    }
                    tasks.add(dateTime == null ? new Deadline(name, parts[3], done)
                            : new Deadline(name, dateTime, done));
                } else if (identifier.equals("E") && parts.length == 5) {
                    tasks.add(new Event(name, parts[3], parts[4], done));
                }
            }
        } catch (IOException e) {
            System.out.println("Error when reading tasks: " + e);
        }
        return tasks;
    }

    public void updateTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    Todo todo = (Todo) task;
                    writer.write(todo.toStringStorage());
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    writer.write(deadline.toStringStorage());
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    writer.write(event.toStringStorage());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error when updating tasks: " + e);
        }
    }
}
