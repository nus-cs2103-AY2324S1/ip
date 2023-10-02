package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents a class for loading data from a file.
 */
public class LoadFile {
    private String filepath;
    private File file;

    /**
     * Constructs a LoadFile object with the specified file path.
     *
     * @param filepath The file path to the data file.
     */
    public LoadFile(String filepath)  {
        this.filepath = filepath;
    }

    /**
     * Gets the loaded file.
     *
     * @return The loaded file.
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Gets the file path of the data file.
     *
     * @return The file path.
     */
    public String getFilepath() {
        return this.filepath;
    }

    /**
     * Loads data from the specified file and processes it into tasks.
     *
     * @throws FileNotFoundException If the specified file is not found.
     */
    public void load() throws FileNotFoundException {

        File f = new File(getFilepath());
        this.file = f;
        Scanner s = new Scanner(getFile());
        while (s.hasNext()) {
            String line = s.nextLine();
            TaskList task = new TaskList(line);
            task.load();
        }
        s.close();
    }
}
