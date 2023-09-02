package storage;

import java.io.File;
import java.io.IOException;

public class Storage {
    /** The file for reading/writing the tasks from/to */
    private File file;

    /**
     * Constructs the storage which will create a new directory called "data" and a new file called "duke.txt" in data
     * if not already created.
     */
    public Storage() {
        try {
            File dataDirectory = new File("./data");
            dataDirectory.mkdir();
            File taskFile = new File("./data/duke.txt");
            if (taskFile.createNewFile()) {
                System.out.println("Created new file to store your tasks!");
            } else {
                System.out.println("Existing task file exists. ");
            }
            this.file = taskFile;
        } catch (IOException e) {
            System.out.println("Unable to create file.");
            e.printStackTrace();
        }
    }

    /**
     * Returns the file that was created during the instantiation of this Storage object.
     *
     * @return The file where the tasks are being read from or written to.
     */
    public File getFile() {
        return this.file;
    }
}
