package Duke;

import Duke.Exceptions.DukeException;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.UI.Ui;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Duke {
    private static final String SEPARATOR_LINE = "____________________________________________________________";
//    private static Duke.Tasks.TaskList listContainer = new Duke.Tasks.TaskList();

    private static String NAME_EMPTY = "\uD83D\uDE21 Your item name cannot be empty!";
    private static String UNKNOWN_COMMAND = "\uD83D\uDE21 This command is not something I can handle!";
    private static String DEADLINE_EMPTY = "\uD83D\uDE21 Missing deadline!";

    private static String FROM_EMPTY = "\uD83D\uDE21 Missing from!";
    private static String TO_EMPTY = "\uD83D\uDE21 Missing to!";
    private static String TIME_FORMAT_ERROR = "\uD83D\uDE21 Time format invalid!";
    private static String FILE_PARSE_ERROR = "\uD83D\uDE21 Error parsing save file!";
    private static String INVALID_DATE_FORMAT = "\uD83D\uDE21 Invalid date format! Try using YYYY-MM-DD";

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static Path SAVE_FILE_LOCATION = Paths.get("data", "duke.txt");
    private static Path SAVE_FILE_DIR = Paths.get("data");


    private Storage storage;
    private Ui ui;
    private TaskList listContainer = new TaskList(new ArrayList<>());

    /**
     * Constructor for our chatbot.
     *
     * @param filePath Specifies where the save file to store previous information is to be saved.
     */
    public Duke(String filePath) {

        this.storage = new Storage(filePath);

        try {
            this.listContainer = new TaskList(storage.load());
            ui = new Ui(this.listContainer, this.storage);
        } catch (DukeException e) {
            System.out.println(e.printError());
        }
    }

    /**
     * Begins the chatbot.
     */
    private void run() {
        ui.beginLogging();
    }

    public static void main(String[] args) {
        new Duke(String.valueOf(SAVE_FILE_LOCATION)).run();
    }
}

