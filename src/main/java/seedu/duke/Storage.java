package seedu.duke;
import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Path;

/**
 * use to intereact with duke.txt a txt file that stores the TaskList.
 *
 * @param directory The file path to where your list.
 */
class Storage {
    private String directory;

    Storage(String directory) {
        this.directory = directory;
    }

    public void createFiles() {
        File dataFile = new File(directory);
        try {
            Path folder = Paths.get("./data/");
            if (!Files.exists(folder)) {
                Files.createDirectory(folder);
            }
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("an error occured");
        }
    }

    public String load() {
        String fileContent = "";
        try {
            createFiles();
            Path path = Paths.get(directory);
            fileContent = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e);
        }
        return fileContent;
    }

    public void save(TaskList tasks) {
        try {
            String content = tasks.toString(); // Get the string representation of tasks
            Files.write(Paths.get(directory), content.getBytes());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
