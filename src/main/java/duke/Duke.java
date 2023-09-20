package duke;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The type Duke.
 */
public class Duke {
    public static final Path PATH_OF_DIRECTORY = Paths.get("./data/duke.txt");

    /**
     * Instantiates a new Duke.
     */
    public Duke() {
        try {
            Storage.readFromDisk(PATH_OF_DIRECTORY, TaskList.getStoreTask());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getResponse(String input) {
        try {
            String dukeResponse = Parser.parse(input);
            assert dukeResponse != null;
            return dukeResponse;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "Invalid character input";
        } catch (IndexOutOfBoundsException e) {
            return "Invalid entry / Task not in list... Please try again...";
        } catch (IllegalArgumentException e) {
            return "OOPS!!! Sorry, but i do not know what that means :-(";
        }

    }

}
