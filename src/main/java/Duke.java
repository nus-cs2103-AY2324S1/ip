import java.util.Scanner;
import duke.TaskList.TaskList;
import duke.Ui.Ui;
import duke.Parser.Parser;
import duke.Storage.Storage;
import duke.DukeException.DukeException;
/**
 * Chatbot that can list tasks.
 */
public class Duke {
    /**
     * Variable to store the list of tasks.
     */
    private TaskList tasks;
    /**
     * Variable to get the reply.
     */
    private Ui ui;
    /**
     * Variable to get the input from the user.
     */
    private Scanner sc;
    /**
     * Variable to access the file where alll of the task is stored.
     */
    private Storage storage;

    /**
     * Constructs the instance of the Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.sc = new Scanner(System.in);
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        this.ui.Hello();
        try {
            this.storage.createStorage();
            this.tasks = this.storage.getStorage();
        } catch (DukeException e) {
            this.ui.error(e);
        }
        while (this.sc.hasNextLine()) {
            try {
                String input = this.sc.nextLine();
                Parser.parse(input, ui, storage, tasks);
                if (input.toUpperCase().equals("BYE")) {
                    this.sc.close();
                    break;
                }
            } catch (DukeException e) {
                this.ui.error(e);
            }
        }
    }

    /**
     * Start the chatbot.
     * @param args args.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}