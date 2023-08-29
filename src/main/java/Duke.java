import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * Encapsulates the logic of a Chat bot
 *
 * @author Rayson
 */
public class Duke {

    // CONSTANTS
    private static final String DIR_NAME = "data";
    private static final String FILE_NAME = "duke.txt";


    public static void main(String[] args) {
        Storage storage = new Storage(DIR_NAME + File.separator + FILE_NAME);
        TaskList tasks = new TaskList(storage.loadTasksFromStorage());
        Ui ui = new Ui();

        ui.showWelcome();

        try {
            
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }
}
