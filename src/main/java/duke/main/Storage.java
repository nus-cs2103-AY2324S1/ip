package duke.main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import duke.exception.InvalidFileException;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Storage to store tasks to a specified filepath.
 */
public class Storage {
    private final String filepath;

    // Mapper to map from Object to JSON format.
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // Setting Mapper to serialize LocalDate to JSON.
    static {
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    /**
     * Initializes storage with filepath.
     *
     * @param filepath Filepath to save and load tasks
     */
    public Storage (String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from filepath (preferably JSON).
     * If the file is not found or corrupted, throws InvalidFileException.
     *
     * @return Tasks
     */
    public ArrayList<Task> load() throws InvalidFileException {
        try {
            return MAPPER.readValue(new File(filepath), new TypeReference<>() {});
        } catch (FileNotFoundException e) {
            throw new InvalidFileException("File not found, will generate file on the next save...");
        } catch (IOException e) {
            throw new InvalidFileException("Uh oh, file is corrupted, can't be loaded, "
                    + "will generate a new file on the next save...");
        }
    }

    /**
     * Saves tasks given by the caller to the filepath.
     *
     * @param tasks Tasks to be saved.
     */
    public void save(ArrayList<Task> tasks) {
        try {
            String jsonString = MAPPER.writerFor(new TypeReference<ArrayList<Task>>() {})
                    .writeValueAsString(tasks);
            Files.writeString(Path.of(filepath), jsonString, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
