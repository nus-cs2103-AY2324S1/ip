import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        try {
            String directoryPath = file.getParent();
            File directory = new File(directoryPath);

            if (!directory.exists()) {
                directory.mkdirs();
                System.out.println("Directory created: " + directoryPath);
            }

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + filePath);
            }
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                String taskType = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1") ? true : false;
                String description = parts[2].trim();

                if (taskType.equals("T")) {
                    // Create a ToDo task
                    tasks.add(new ToDo(description, isDone));
                } else if (taskType.equals("D")) {
                    // Extract 'by' from parts[3] if applicable
                    LocalDate by = LocalDate.parse(parts[3].trim());
                    // Create a Deadline task
                    tasks.add(new Deadline(description, by, isDone));
                } else if (taskType.equals("E")) {
                    // Extract 'from' and 'to' from parts[3] and parts[4] if applicable
                    String from = parts[3].trim();
                    String to = parts[4].trim();
                    // Create an Event task
                    tasks.add(new Event(description, from, to, isDone));
                } else {
                    // Handle unsupported task type
                    throw new DukeException("☹ OOPS!!! I'm sorry, but there's an error loading the file");
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but there's an error loading the file");
        }
    }

    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks.getAll()) {
                // Convert each task to its string representation and write to file
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but there's an error loading the file");
        }
    }
}
