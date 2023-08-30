import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;


/**
 * The main class for Duke Chatbot
 */
public class Duke {

    /** Variable to store task list */
    private Storage storage;

    /** Variable to handle list of tasks operations */
    private TaskList tasks;

    /** Variable to handle user interactions */
    private Ui ui;

    /** Variable to handle user inputs */
    private Parser parser;
    
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            if (storage.fileExists()) {
                tasks = new TaskList(storage.loadTasksData());
            } else {
                tasks = new TaskList();
            }
        } catch (FileNotFoundException e) {
            System.out.println("!ERROR! " + e);
        } catch (IOException e) {
            System.out.println("!ERROR! " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("!ERROR! " + e);
        }
    }

    public void run() {
        ui.greetings();
        Boolean endSession = true;
        while(endSession) {
            try {
                String userInput = ui.inputSession();
                Input parsedInput = parser.parse(userInput);
                endSession = ui.handleInput(tasks, parsedInput);
                tasks.overwriteTasksData(storage);
            } catch (DukeException e) {
                System.out.println("!ERROR! " + e);
            } catch (FileNotFoundException e) {
                System.out.println("!ERROR! " + e);
            } catch (IOException e) {
                System.out.println("!ERROR! " + e);
            } catch (DateTimeParseException e) {
                System.out.println("!ERROR! " + e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }
}
