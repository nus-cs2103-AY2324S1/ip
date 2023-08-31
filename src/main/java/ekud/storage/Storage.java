package ekud.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Paths;

import ekud.command.ByeCommand;
import ekud.command.Command;
import ekud.command.ListCommand;
import ekud.error.StorageException;

/**
 * Facilitates interacting with the file that persists program data.
 * Ekud saves program data by writing the commands run by the user directly to
 * the file, similar to logging the commands. These commands are then replayed
 * when the Ekud program starts up. Eventually, a flattening algorithm will be
 * developed to save the extra space that unnecessary commands take up.
 */
public final class Storage {
    private final File dataFile;
    private final PrintStream out;

    /**
     * Creates the save file if it doesn't exist.
     */
    public Storage() {
        try {
            File dataDir = Paths.get("data").toFile();
            dataDir.mkdir();
            dataFile = Paths.get("data", "ekud.txt").toFile();
            dataFile.createNewFile();
            out = new PrintStream(new FileOutputStream(dataFile, true));
        } catch (IOException error) {
            throw new StorageException("Failed to create save file.");
        } catch (SecurityException error) {
            throw new StorageException("Failed to create save file.");
        }
    }

    /**
     * Creates an input stream from the save file to read data.
     * 
     * @return The create input stream.
     */
    public InputStream createInputStream() {
        try {
            return new FileInputStream(dataFile);
        } catch (FileNotFoundException error) {
            throw new StorageException("Failed to read save file.");
        }
    }

    /**
     * Writes a new command to the save file.
     * 
     * @param command The command to write to the save file.
     */
    public void write(Command command) {
        if (command instanceof ByeCommand || command instanceof ListCommand) {
            return;
        }
        out.println(command);
    }
}
