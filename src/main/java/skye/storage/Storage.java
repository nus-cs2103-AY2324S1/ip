package skye.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import skye.data.exception.DukeException;
import skye.data.task.Task;
import skye.data.venue.Venue;
import skye.parser.TaskDecoder;
import skye.parser.VenueDecoder;

/**
 * Represents a storage utility object which provides operations to load task information
 * from the user's file system and write task information to the specified file path.
 */
public class Storage {

    private static final String HELP_GUIDE_DIRECTORY = "src/main/java/skye/guide/help.txt";
    private static final String VENUE_DIRECTORY = "data/venues.txt";
    private final String filePath;
    private final TaskDecoder taskDecoder;
    private final VenueDecoder venueDecoder;

    /**
     * Initializes the storage object by providing a file path
     *
     * @param filePath Path to the save file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskDecoder = new TaskDecoder();
        this.venueDecoder = new VenueDecoder();
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
    public List<Task> load() throws IOException, DateTimeParseException, DukeException {
        List<Task> tasks = new ArrayList<>();
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        } else {
            List<String> encodedTasks = Files.readAllLines(path);
            for (String encodedTask : encodedTasks) {
                tasks.add(taskDecoder.decode(encodedTask));
            }
        }
        return tasks;
    }

    /**
     * Loads the help guide.
     *
     * @return Help Guide as a list of strings
     * @throws IOException Signifies that loading the file has failed
     */
    public List<String> loadHelpGuide() throws IOException {
        return Files.readAllLines(Paths.get(HELP_GUIDE_DIRECTORY));
    }


    /**
     * Writes a list of decoded Task objects to the specified file path.
     *
     * @param tasks List of Task objects
     * @throws IOException Signifies that writing to file has failed
     */
    public void write(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(Paths.get(filePath).toString());
        for (Task t: tasks) {
            fw.write(t.toSaveDataFormat() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Load all venue information from the save file and decoding each line into a Venue object.
     *
     * @return Venues
     * @throws IOException Signifies that writing to file has failed
     * @throws DukeException Describes the error encountered when executing the command
     */
    public List<Venue> loadVenues() throws IOException, DukeException {
        List<Venue> venues = new ArrayList<>();
        Path path = Paths.get(VENUE_DIRECTORY);
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        } else {
            List<String> encodedVenues = Files.readAllLines(path);
            for (String encodedVenue : encodedVenues) {
                venues.add(venueDecoder.decode(encodedVenue));
            }
        }
        return venues;
    }

    /**
     * Writes a list of decoded Venue objects to the specified file path.
     *
     * @param venues List of Venue objects
     * @throws IOException Signifies that writing to file has failed
     */
    public void writeVenue(List<Venue> venues) throws IOException {
        FileWriter fw = new FileWriter(Paths.get(VENUE_DIRECTORY).toString());
        for (Venue venue : venues) {
            fw.write(venue.toSaveDataFormat() + System.lineSeparator());
        }
        fw.close();
    }
}
