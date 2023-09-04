import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private final String dataFilePath;

    public Storage(String filePath) {
        this.dataFilePath = filePath;
    }

    public List<Task> load() {
        List<Task> list = new ArrayList<>();
        try {
            File file = new File(dataFilePath);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(dataFilePath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        Task task = Parser.parseTask(line);
                        if (task != null) list.add(task);
                    }
                }
            } else {
                System.out.println("No saved tasks found.");
            }
        } catch (IOException | SecurityException e) {
//            throw new DukeException("Error loading tasks from the file.");
        }
        return list;
    }

    public void save(List<Task> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFilePath))) {
            for (Task task : list) {
                writer.write(task.fileSaveFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to the file.");
        }
    }
}
