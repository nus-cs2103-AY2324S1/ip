package storage.save;

import storage.TaskList;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *  Encapsulates a class to help load Kniaz tasklist data from disk.
 */
public class KniazLoader {

    /**
     * The default path KniazLoader will try to storage.save to
     */
    public static final String DEFAULT_PATH = KniazSaver.DEFAULT_PATH;

    /**
     * Internal File object for reading
     */
    private File saveFile;

    /**
     * Constructor for class. Makes a KniazLoader that will try to load from the provided path.
     * @param path the path to storage.save files to
     */
    public KniazLoader(String path) {
        this.saveFile = new File(path);
    }

    /**
     * Constructor for class. Makes a KniazLoader that will try to load from provided path
     */
    public KniazLoader() {
        this(DEFAULT_PATH);
    }

    /**
     * Attempts to load saved TaskList from the location that was previously provided to this class,
     * during construction
     * @return the loaded TaskList
     * @throws IOException If there is an error in I/O, typically caused by the file not existing at the location,
     * which may be caused by this being the first ever session of Kniaz
     * @throws SecurityException If the security manager did not allow this class to read from file
     * @throws ClassNotFoundException If the data loaded did not have an entry for the class name.
     * Typically caused by corruped data
     */
    public TaskList load() throws IOException, SecurityException, ClassNotFoundException {

        if (!this.saveFile.exists()) {
            throw new IOException(String.format("%s does not exist",this.saveFile.getAbsolutePath()));
        }

        if (!this.saveFile.canRead()) {
            this.saveFile.setReadable(true);
        }

        // time to read it
        FileInputStream fileInputStream = new FileInputStream(this.saveFile);
        ObjectInputStream taskInputStream = new ObjectInputStream(fileInputStream);

        return (TaskList) taskInputStream.readObject();

    }

}
