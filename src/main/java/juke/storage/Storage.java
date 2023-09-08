package juke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import juke.commons.classes.JukeObject;
import juke.exceptions.JukeInitialisationException;
import juke.exceptions.storage.JukeStorageException;
import juke.exceptions.storage.JukeStorageReadException;
import juke.exceptions.storage.JukeStorageWriteException;
import juke.parsers.FileParser;
import juke.tasks.JukeTask;

/**
 * Manages the storage and retrieval of data from the data file.
 */
public class Storage extends JukeObject {
    /** Path to the data directory. */
    private static final Path DIRECTORY_PATH = Paths.get("./data");

    /** Path to the data file. */
    private static final Path FILE_PATH = Paths.get("./data/tasks.txt");

    /**
     * Creates an instance of {@code Storage}. Prevents external instantiation through
     * any methods other than {@code Storage.of()}.
     */
    private Storage() {
        super();
    }

    /**
     * Creates an instance of {@code Storage} and creates the files
     * and directories required if necessary.
     *
     * @return {@code Storage} instance
     * @throws JukeInitialisationException if the directories or files cannot be
     *     created or initialised
     */
    public static Storage of() throws JukeInitialisationException {
        // if the directory does not exist, create the directory
        if (!Files.exists(Storage.DIRECTORY_PATH)) {
            try {
                Files.createDirectory(Storage.DIRECTORY_PATH);
            } catch (IOException ex) {
                throw new JukeInitialisationException("Oh no! I am unable to create a directory to store your "
                                                              + "tasks! Please try again later!");
            }
        } else if (!Files.exists(Storage.FILE_PATH)) {
            // if the dir exist but file doesn't, then just create the file
            try {
                Files.createFile(Storage.FILE_PATH);
            } catch (IOException ex) {
                throw new JukeInitialisationException("Oh no! I am unable to create a datafile to store your "
                                                              + "tasks! Please try again later!");
            }
        }

        // the file and directory should exist if execution reaches here
        assert Files.exists(Storage.FILE_PATH);
        assert Files.exists(Storage.DIRECTORY_PATH);

        return new Storage();
    }

    /**
     * Returns a list of saved task from the datafile.
     *
     * @return List of {@code JukeTasks} retrieved
     * @throws JukeStorageException If the file could not be opened or processed for any reason
     */
    public List<JukeTask> read() throws JukeStorageReadException {
        try (BufferedReader br = Files.newBufferedReader(Storage.FILE_PATH)) {
            String curr;
            List<JukeTask> tasks = new LinkedList<>();

            while ((curr = br.readLine()) != null) {
                tasks.add(FileParser.parseTask(curr));
            }

            return tasks;
        } catch (IOException ex) {
            throw new JukeStorageReadException("Oh no! I am unable to understand the data stored "
                                                       + "in the datafile!");
        }
    }

    /**
     * Writes the list of tasks into the datafile.
     *
     * @param tasks List of {@code JukeTasks} to write
     * @throws JukeStorageException If the file could not be opened or written to for any reason
     */
    public void write(List<JukeTask> tasks) throws JukeStorageWriteException {
        try (BufferedWriter bw = Files.newBufferedWriter(Storage.FILE_PATH)) {
            for (JukeTask t : tasks) {
                bw.write(t.save());
                bw.newLine();
            }
        } catch (IOException ex) {
            throw new JukeStorageWriteException("Oh no! I cannot save your data to the datafile!");
        }
    }
}
