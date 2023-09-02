package duke;
import java.util.Scanner;

/**
 * Duke is a chat bot that allows users to add, delete, mark, view tasks.
 */
public class Duke {

    private Storage storage;
    private TaskList list;
    private Ui ui;
    private boolean isActive = true;
    private final String FILE_PATH;

    /** Creates a new Duke chat bot
     *
     * @param filePath Path to file to read stored tasklist.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.FILE_PATH = filePath;
        this.storage = new Storage(filePath);
        this.list = new TaskList(storage.load());
    }

    /** Starts the Duke chat bot. */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(this.list);
        this.ui.greet();
        while (this.isActive) {
            String input = scanner.nextLine();
            try {
                isActive = parser.parse(input);
            } catch (Exception error) {
                this.ui.showError(error.getMessage());
            }
        }
        this.exit();
    }

    /** Stops the Duke chat bot */
    public void exit() {
        this.isActive = false;
        this.list.updateStorage(FILE_PATH);
        this.ui.farewell();
    }

    public static void main(String[] args) {
        new Duke("./src/main/java/duke/data.txt").run();
    }
}
