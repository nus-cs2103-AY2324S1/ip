package taskmate.tools;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This Storage class contains the methods to save the task data to the user's machine.
 * Its `saveFilePath` attribute is the relative file path to save the tasks data with respect to the current working
 * directory.
 * Note: If the "/data/" directory does not exist in the specified file path, it will be created.
 */
public class Storage {

    private final String saveFilePath;

    /**
     * A constructor for Storage objects to store tasks data to the user's machine. It takes in a relative file path to
     * save the data.
     * @param filePath A String object representing the relative file path to save the user's task data
     */
    public Storage(String filePath) {
        assert filePath != null;
        this.saveFilePath = filePath;
    }

    public String getSaveFilePath() {
        return this.saveFilePath;
    }

    public String getAbsoluteSaveFilePath() {
        return System.getProperty("user.dir")
                + this.getSaveFilePath().substring(1).replace("/", "\\");
    }

    /**
     * Reads task data from `saveFilePath` and if the data can be found, returns it as a String object. If the file
     * cannot be found at the path, an IOException is thrown.
     * @return A String object that contains the file contents of the previously saved task data
     * @throws IOException This exception is thrown when the file cannot be found at the specified data path
     */
    public String readFromFile() throws IOException {
        Path filePath = Path.of(saveFilePath);
        return Files.readString(filePath);
    }

    /**
     * Writes `text` to the file path. If the file path does not exist, this method creates the directory to save the
     * tasks data.
     * @param text A String object representing the task data to be written to the file
     * @throws IOException An IOException is thrown when the file cannot be written into that folder
     */
    public void writeToFile(String text) throws IOException {

        assert saveFilePath != null;

        try {
            Files.createDirectories(Paths.get(getDirectoryPath(saveFilePath)));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        FileWriter fw = new FileWriter(saveFilePath);
        fw.write(text);
        fw.close();
    }

    static String getDirectoryPath(String filePath) {
        Path path = Paths.get(filePath);
        Path directoryPath = path.getParent();

        if (directoryPath != null) {
            return directoryPath.toString();
        } else {
            return null;
        }
    }


}
