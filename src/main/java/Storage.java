import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private String filepath;
    private Path path;
    Storage(String filepath) {
        this.filepath = filepath;
        this.path = Paths.get(this.filepath);
    }
    /**
     * Create the directory and file to store the previous list, else update it to the current list.
     */
    public void createFileIfNotExists() {
        Path directoryPath = this.path.getParent();

        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
                System.out.println("directory created");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!Files.exists(this.path)) {
            try {
                Files.createFile(this.path);
                System.out.println("file created");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Update the list of tasks in the duke.txt according to the current list
     *
     * @param tasks the ArrayList of tasks that were added
     */
    public void saveTasksToFile(TaskList tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.path.toFile()))) {
            for (int i = 0; i < tasks.size(); i++) {
                int currentNumber = i + 1;
                writer.write(tasks.get(currentNumber).toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the list of tasks stored in the duke.txt file and return the ArrayList of tasks.
     *
     * @return The ArrayList of tasks saved from previous usage.
     */


    public ArrayList<Task> loadTasksFromFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String taskType = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
                // Convert String back to Task object
                if (taskType.equals("T")) {
                    System.out.println(line);
                    tasks.add(ToDo.parseFromString(line));
                } else if (taskType.equals("D")) {
                    System.out.println(line);
                    tasks.add(Deadline.parseFromString(line));
                } else {
                    System.out.println(line);
                    tasks.add(Event.parseFromString(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
