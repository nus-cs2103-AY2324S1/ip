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

public final class Storage {
    private final File dataFile;
    private final PrintStream out;

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

    public InputStream createInputStream() {
        try {
            return new FileInputStream(dataFile);
        } catch (FileNotFoundException error) {
            throw new StorageException("Failed to read save file.");
        }
    }

    public void write(Command command) {
        if (command instanceof ByeCommand || command instanceof ListCommand) {
            return;
        }
        out.println(command);
    }
}
