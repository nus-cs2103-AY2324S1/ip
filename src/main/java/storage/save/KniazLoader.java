package storage.save;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import exceptions.KniazRuntimeException;
import storage.TaskList;


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
     * @param path the path to save files to
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
     * @throws  KniazRuntimeException when something goes wrong in retrieving data, will wrap around the root exception.
     */
    public TaskList load() throws KniazRuntimeException {

        if (!this.saveFile.exists()) {
            throw new KniazRuntimeException("Savefile does not exist",
                    "There is no saved data of your tasklist. We shall start from scratch.",
                    new IOException(String.format("%s does not exist", this.saveFile.getAbsolutePath())));
        }

        if (!this.saveFile.canRead()) {
            try {
                this.saveFile.setReadable(true);
            } catch (SecurityException e) {
                throw new KniazRuntimeException("Could not set savefile to read!",
                        "I could not access your tasklist saved data. We shall start from scratch.",
                        e);
            }

        }

        // time to read it


        try {
            FileInputStream fileInputStream = new FileInputStream(this.saveFile);
            ObjectInputStream taskInputStream = new ObjectInputStream(fileInputStream);
            return (TaskList) taskInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new KniazRuntimeException("Missing class in saved file, probably corrupted",
                    "I could not interpret your savefile data. We shall start from scratch.",
                    e);

        } catch (ClassCastException e) {
            throw new KniazRuntimeException("Could not cast savefile class, probably corrupted",
                    "I could not interpret your savefile data. We shall start from scratch.",
                    e);

        } catch (IOException e) {
            throw new KniazRuntimeException("Savefile does not exist",
                    "There is no saved data of your tasklist. We shall start from scratch.",
                    new IOException(String.format("%s does not exist", this.saveFile.getAbsolutePath())));
            // should not normally be thrown because we verify up above, but to satisfy compiler requirements
        }



    }

}
