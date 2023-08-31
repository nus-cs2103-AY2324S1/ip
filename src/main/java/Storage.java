import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Storage {
    private final String filePath;

    public Storage() {
        this.filePath = "./data/Moss.txt";
    }

    public List<Task> loadTasks() throws MossException{
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] tokens = line.split(" \\| ");
                    String type = tokens[0];
                    boolean marked = Objects.equals(tokens[1], "X");
                    String description = tokens[2];
                    Task task;
                    switch (type) {
                        case "T":
                            task = new ToDo(description);
                            break;
                        case "D":
                            String by = tokens[3];
                            LocalDate date = LocalDate.parse(by);
                            task = new Deadline(description, date);
                            break;
                        case "E":
                            String from = tokens[3];
                            LocalDate fromDate = LocalDate.parse(from);
                            String to = tokens[4];
                            LocalDate toDate = LocalDate.parse(to);

                            task = new Event(description, fromDate, toDate);
                            break;
                        default:
                            throw new MossException("invalid task type");
                    }

                    if (marked){
                        task.markDone();
                    }
                    tasks.add(task);

                }
                reader.close();
            }
        } catch (IOException e) {
            throw new MossException("error loading");
        }
        return tasks;
    }

    public void saveTasks(List<Task> tasks) throws MossException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            List<String> data = new ArrayList<>();
            for (Task task : tasks) {
                data.add(task.toString());
            }
            Files.write(Path.of(filePath), data);
            writer.close();
        } catch (IOException e) {
            throw new MossException("Saving error");
        }
    }
}

// Example usage


