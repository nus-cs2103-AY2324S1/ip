package duke.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The Storage class is responsible for reading and writing data to/from storage files
 * in the Duke application.
 */
public class Storage {
    enum Type {
        INTEGER,
        STRING
    }
    private final String filepath;

    /**
     * Constructs a new Storage object with the specified file path and folder path.
     * If the file does not exist, it creates the file.
     *
     * @param filepath   The path to the storage file.
     * @param folderpath The path to the folder where the storage file should be located.
     */
    protected Storage(String filepath, String folderpath) {
        this.filepath = filepath;
        File file = new File(filepath);
        try {
            Files.createDirectories(Paths.get(folderpath));
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads data from the storage file and returns a list of tasks.
     *
     * @return A list of Task objects loaded from the storage file.
     */
    protected ArrayList<Task> load() {
        ArrayList<String> taskData = new ArrayList<>();
        try {
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                taskData.add(line);
            }
            scanner.close();
            return FileIO.readCsv(taskData);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DukeException e) {
            System.out.println(Response.generate(e.toString()));
        }
        return new ArrayList<>();
    }

    /**
     * Saves a list of task data to the storage file.
     *
     * @param taskData A list of task data to be saved to the storage file.
     */
    protected void save(ArrayList<String> taskData) {
        try {
            FileWriter writer = new FileWriter(filepath);

            for (String line : taskData) {
                writer.write(line + "\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
