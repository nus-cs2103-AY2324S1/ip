package seedu.duke;
import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Path;

/**
 * use to intereact with duke.txt a txt file that stores the TaskList.
 *
 * @param directory The file path to where the stored string is
 */
class Storage {
    private String directory;

    Storage(String directory) {
        this.directory = directory;
    }

    /**
     * use to create the data folder and the duke.txt file inside of the folder
     */
    public void createFiles() {
        File storagefile = new File(directory);
        try {
            Path folder = Paths.get("./data/");
            if (!Files.exists(folder)) {
                Files.createDirectory(folder);
            }
            if (!storagefile.exists()) {
                storagefile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("an error occured while creating file");
        }
    }

    /**
     * use to get the string inside the duke.txt file
     */
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


    /**
     * used to saved the to_string of the tasklist back into the duke.txt file as a text
     *
     * @param TaskList that needs to be saved
     */
    public void save(TaskList tasks) {
        try {
            String content = tasks.toString(); // Get the string representation of tasks
            Files.write(Paths.get(directory), content.getBytes());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
