package seedu.duke;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    public String load() {
        String fileContent = "";
        try {
            System.out.println("Working Directory: " + System.getProperty("user.dir"));
            byte[] encodedBytes = Files.readAllBytes(Paths.get(directory));
            fileContent = new String(encodedBytes);
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
