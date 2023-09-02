import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String filePath;
    private Ui ui;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.ui = new Ui();
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks the list of tasks to save.
     */
    public void saveToFile(List<Task> tasks) {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.toFileFormat()); 
        }
        
        Path path = Paths.get(filePath);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
            Files.write(path, lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            ui.printMessage("Error saving tasks to file.");
        }
    }

    /**
     * Loads tasks from the file.
     *
     * @return a list of tasks loaded from the file.
     */
    public ArrayList<Task> loadFromFile() {
        Path path = Paths.get(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    if (line.trim().isEmpty()) 
                        continue;
                    try {
                        tasks.add(Task.fromFileFormat(line));
                    } catch (IllegalArgumentException e) {
                        ui.printMessage("Data file might be corrupted. (i.e., content not in expected format.)");
                    }
                }
            } catch (IOException e) {
                ui.printMessage("Error reading tasks from file.");
            }
        }

        return tasks;
    }
}
