package storage.save;

import storage.TaskList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Encapsulates a class to help storage.save Kniaz tasklist data to disk.
 */
public class KniazSaver {

    /**
     * The default path KniazSaver will try to storage.save to
     */
    public final static String DEFAULT_PATH = "./data/Kniaz.dat";

    /**
     * Internal File object for writing
     */
    private File saveFile;

    /**
     * Constructor for class. Makes a KniazSaver that will try to storage.save at the provided path.
     * @param path the path to storage.save files to
     */
    public KniazSaver(String path) {
        this.saveFile = new File(path);
    }

    /**
     * Constructor for class. Makes a KniazSaver that will try to storage.save to DEFAULT_PATH
     */
    public KniazSaver() {
        this(DEFAULT_PATH);
    }

    /**
     * Saves the provided TaskList at the location this class was constructed with.
     * @param taskList the TaskList to storage.save to disk
     * @throws java.io.IOException If there is an error with I/O,
     * typically when an error occured in creating file or writing to file
     * @throws java.lang.SecurityException If the security manager did not allow this class to write to file
     *
     */
    public void save(TaskList taskList) throws java.io.IOException,java.lang.SecurityException {

        if (!this.saveFile.getParentFile().exists()) {
            // Strictly speaking, do not need to wrap in an if.
            // Makes directory if file does not exist
            this.saveFile.getParentFile().mkdirs(); // may throw IOException
        }
        if (!this.saveFile.exists()) {
            // Also does not strictly need to be wrapped in an if block
            // makes file if the file does not exist yet
            this.saveFile.createNewFile(); // May throw IOException
        }
        if (!this.saveFile.canWrite()) {
            // Again, does not strictly need to be wrapped
            // Attempt to allow access to write to the file if possible
            this.saveFile.setWritable(true); //May throw SecurityException
        }

        // Below syntax handles setting up of streams to write
        FileOutputStream fileOutputStream = new FileOutputStream(this.saveFile);
        ObjectOutputStream taskListOutputStream = new ObjectOutputStream(fileOutputStream);

        // This line does the writing to the data file
        taskListOutputStream.writeObject(taskList);


    }

}
