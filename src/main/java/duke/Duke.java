package duke;
import java.util.Scanner;

/** Duke is a chat bot that allows users to add, delete, mark, view tasks. */
public class Duke {

    private Storage storage;
    private TaskList list;
    private Ui ui;
    private final String FILE_PATH;
    private Parser parser;

    /** Creates a new Duke chat bot
     *
     * @param filePath Path to file to read stored task list.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.FILE_PATH = filePath;
        this.storage = new Storage(filePath);
        this.list = new TaskList(storage.load());
        this.parser = new Parser(this.list, filePath);
    }

    public String respond(String input) {
        try {
            return parser.parse(input);
        } catch (Exception error) {
            return error.getMessage();
        }
    }
}
