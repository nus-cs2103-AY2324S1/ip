package moss;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Storage {

    private final String FILE_PATH;

    public Storage() throws MossException {
        this.FILE_PATH = "./data/Moss.txt";
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                Files.createDirectories(Path.of(this.FILE_PATH).getParent());
                Files.createFile(Path.of(this.FILE_PATH));
            } catch (IOException e) {
                throw new MossException("Could not create");
            }
        }
    }

    public List<Task> loadTasks() throws MossException {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
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

                    if (marked) {
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            List<String> data = new ArrayList<>();
            for (Task task : tasks) {
                data.add(task.toString());
            }
            Files.write(Path.of(FILE_PATH), data);
            writer.close();
        } catch (IOException e) {
            throw new MossException("Saving error");
        }
    }
}


// Example usage


