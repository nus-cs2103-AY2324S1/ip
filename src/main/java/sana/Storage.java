package sana;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Handles the loading, saving, and clearing of tasks from files.
 */
public class Storage {
    private String filePath;
    private String folderPath;
    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath specifies the path where the file to store tasks is saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.folderPath = String.valueOf(Paths.get(filePath).getParent());
    }

    /**
     * Loads tasks from the file and returns them as a formatted string.
     *
     * @return a formatted string containing the tasks loaded from the file.
     */
    public String load() {
        StringBuilder tasks = new StringBuilder();
        try {
            Scanner scan = new Scanner(new File(Paths.get(filePath).toString()));
            while (scan.hasNextLine()) {
                String task = scan.nextLine();
                tasks = tasks.append(task + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return tasks.toString();
    }

    /**
     * Saves a task to a specified file path.
     *
     * @param task The task to be saved.
     */
    public void save(Task task) {
        File folder = new File(folderPath);

        // Check if folder exists, if not create one
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Check if file exists, if not create one
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        // Write to file
        try {
            FileWriter writer = new FileWriter(Paths.get(filePath).toString(), true);
            writer.write(task.formatTask() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Clears the contents of the specified file.
     */
    public void clear() {
        File file = new File(filePath);
        file.delete();
    }
}
