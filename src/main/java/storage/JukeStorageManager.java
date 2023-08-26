package main.java.storage;

import main.java.exceptions.JukeInitialisationException;
import main.java.exceptions.storage.JukeStorageException;
import main.java.exceptions.storage.JukeStorageReadException;
import main.java.exceptions.storage.JukeStorageWriteException;
import main.java.parsers.JukeFileParser;
import main.java.primitivies.JukeObject;
import main.java.tasks.JukeTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Manages the storage and retrieval of data from the data file.
 */
public class JukeStorageManager extends JukeObject {
    /** Path to the data directory. */
    public static final Path DIR_PATH = Paths.get("./data");

    /** Path to the data file. */
    public static final Path FILE_PATH = Paths.get("./data/tasks.txt");

    /**
     * Creates an instance of {@code JukeStorageManager} and creates the files
     * and directories required if necessary.
     * @return {@code JukeStorageManager} instance
     * @throws JukeInitialisationException if the directories or files cannot be
     * created or initialised
     */
    public static JukeStorageManager of() throws JukeInitialisationException {
        // if the directory does not exist, the file also cannot exist
        if (!Files.exists(DIR_PATH)) {
            try {
                Files.createDirectory(DIR_PATH);
                Files.createFile(FILE_PATH);
            } catch (IOException ex) {
                throw new JukeInitialisationException("Oh no! I am unable to create a directory to store your " +
                                                              "tasks! Please try again later!");
            }
        } else if (!Files.exists(FILE_PATH)){
            // if the dir exist but file doesn't, then just create the file
            try {
                Files.createFile(FILE_PATH);
            } catch (IOException ex) {
                throw new JukeInitialisationException("Oh no! I am unable to create a datafile to store your " +
                                                              "tasks! Please try again later!");
            }
        }

        return new JukeStorageManager();
    }

    /**
     * Returns a list of saved task from the datafile.
     * @return List of {@code JukeTasks}
     * @throws JukeStorageException If the file could not be opened or processed for any reason
     */
    public List<JukeTask> get() throws JukeStorageReadException {
        try (BufferedReader br = Files.newBufferedReader(FILE_PATH)) {
            String curr;
            List<JukeTask> tasks = new LinkedList<>();

            while ((curr = br.readLine()) != null) {
                tasks.add(JukeFileParser.parseTask(curr));
            }

            return tasks;
        } catch (IOException ex) {
            throw new JukeStorageReadException("Oh no! I am unable to understand the data stored " +
                                                          "in the datafile!");
        }
    }

    /**
     * Writes the list of tasks into the datafile.
     * @param tasks List of {@code JukeTasks} to write
     * @throws JukeStorageException If the file could not be opened or written to for any reason
     */
    public void write(List<JukeTask> tasks) throws JukeStorageWriteException {
        try (BufferedWriter bw = Files.newBufferedWriter(FILE_PATH)) {
            for (JukeTask t : tasks) {
                bw.write(t.save());
                bw.newLine();
            }
        } catch (IOException ex) {
            throw new JukeStorageWriteException("Oh no! I cannot save your data to the datafile!");
        }
    }
}
