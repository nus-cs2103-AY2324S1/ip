package skye.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import skye.data.exception.DukeException;
import skye.data.task.Task;
import skye.data.venue.Venue;
import skye.parser.TaskDecoder;
import skye.parser.VenueDecoder;

/**
 * Represents a storage controller that performs read and write operations on task and venue objects.
 */
public class StorageManager {

    private static final String HELP_GUIDE_DIRECTORY = "/guide/help.txt";
    private static final String TASKS_SAVE_FILE_NAME = "tasks.txt";
    private static final String VENUES_SAVE_FILE_NAME = "venues.txt";

    private Storage<Task> taskStorage;
    private Storage<Venue> venueStorage;

    private TaskDecoder taskDecoder;
    private VenueDecoder venueDecoder;

    /**
     * Initializes a storage controller
     *
     * @param saveDirectory Directory to store the save file
     */
    public StorageManager(String saveDirectory) {
        taskStorage = new Storage<>(saveDirectory, TASKS_SAVE_FILE_NAME);
        venueStorage = new Storage<>(saveDirectory, VENUES_SAVE_FILE_NAME);
        taskDecoder = new TaskDecoder();
        venueDecoder = new VenueDecoder();
    }

    /**
     * Loads the help guide.
     *
     * @return Help Guide as a list of strings
     * @throws IOException Signifies that loading the file has failed
     */
    public List<String> loadHelpGuide() throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        InputStream inputStream = this.getClass().getResourceAsStream(HELP_GUIDE_DIRECTORY);
        assert inputStream != null;

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        reader.close();

        return lines;
    }

    /**
     * Load all task information from the save file by reading from a text file specified
     * by the file path and decoding each line into a Task object.
     *
     * @return Tasks
     * @throws IOException Describes the I/O error encountered in the OS file system
     * @throws DateTimeParseException Describes the error with the date format
     * @throws DukeException Describes the error encountered when executing the command
     */
    public List<Task> loadTasks() throws IOException, DateTimeParseException, DukeException {
        return taskStorage.load(taskDecoder);
    }

    /**
     * Load all venue information from the save file and decoding each line into a Venue object.
     *
     * @return Venues
     * @throws IOException Signifies that writing to file has failed
     * @throws DukeException Describes the error encountered when executing the command
     */
    public List<Venue> loadVenues() throws IOException, DukeException {
        return venueStorage.load(venueDecoder);
    }

    /**
     * Writes a list of decoded Task objects to the specified file path.
     *
     * @param tasks List of Task objects
     * @throws IOException Signifies that writing to file has failed
     */
    public void writeTasks(List<Task> tasks) throws IOException {
        taskStorage.write(tasks);
    }

    /**
     * Writes a list of decoded Venue objects to the specified file path.
     *
     * @param venues List of Venue objects
     * @throws IOException Signifies that writing to file has failed
     */
    public void writeVenues(List<Venue> venues) throws IOException {
        venueStorage.write(venues);
    }
}
