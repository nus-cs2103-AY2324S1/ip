package pooh;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class for the Pooh chatbot.
 * <p>
 * This class is responsible for initializing storage, loading tasks, and running
 * the main event loop of the application. It delegates command parsing to the Parser
 * class and storage operations to the Storage class.
 * </p>
 */
public class Pooh {

    private final Storage taskStorage;
    private final Parser cmdParser;
    private TaskList taskList;

    /**
     * Constructs a new Pooh chatbot.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Pooh(String filePath) {

        cmdParser = new Parser();
        taskStorage = new Storage(filePath);
        try {
            taskList = new TaskList(taskStorage.loadTasks());
        } catch (LoadTasksException ex) {
            Ui.respond(ex.toString());
            List<Task> listOfTasks = new ArrayList<Task>();
            taskList = new TaskList(listOfTasks);
        }
    }

    /**
     * Starts the main event loop of the Pooh chatbot.
     * <p>
     * The method will keep listening for user input until the user chooses to exit
     * the application. All recognized commands are parsed and executed through the
     * Parser class.
     * </p>
     */
    public void run() {
        Ui.printWelcomeMsg();
        Scanner userInput = new Scanner(System.in);
        while (userInput.hasNextLine()) {
            try {
                String userCmd = userInput.nextLine();
                cmdParser.parseInput(taskStorage, taskList, userCmd);
            } catch (UnrecognizedCommandException ex) {
                Ui.respond(ex.toString());
            } catch (WriteTasksException ex) {
                Ui.respond(ex.toString());
            } catch (InvalidTaskException ex) {
                Ui.respond(ex.toString());
            }
        }
    }

    public String getResponse(String input) {
        return "hi!";
    }

    /**
     * The main entry point of the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Pooh("pooh.txt").run();
    }
}