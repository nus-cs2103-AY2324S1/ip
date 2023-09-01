package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Storage class represents the file information.
 */
public class Storage {
    /** The file's information **/
    private File file;
    /** The file path **/
    String filePath;

    /**
     * Instantiates an instance of Storage.
     *
     * @param filePath The file path for the new file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            this.file = new File(filePath);
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the file based on the file path of the instance.
     *
     * @return The file stored.
     */
    public File load() {
        return this.file;
    }

    /**
     * Rewrites all the information in the file.
     *
     * @param newText The new information to be written into the file.
     */
    public void rewrite(String newText) {
        try {
            FileWriter fw;
            fw = new FileWriter(filePath);
            fw.write(newText);
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
