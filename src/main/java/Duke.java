import java.util.Scanner;

import duke.Parser;
import duke.Ui;
import storage.Storage;
import taskutil.TaskList;

/**
 * Class to initialise and run the chatbot.
 */
public class Duke {

    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructor for a chatbot instance.
     * @param fileDirectory Directory to store chatbot data.
     */
    public Duke(String fileDirectory) {
        storage = new Storage(fileDirectory);
        taskList = new TaskList();
    }

    /**
     * Initialises chatbot by loading data from file, queries user for decision when file cannot be read.
     */
    public void initialise() {
        if (!storage.loadData(taskList)) { // Error with loading data file, prompts for user input for next step
            boolean isUnresolved = true;
            Scanner userInput = new Scanner(System.in);
            while (isUnresolved) {
                String input = userInput.nextLine().toUpperCase();
                switch (input) {
                case "Y":
                    this.runJyuuni();
                    isUnresolved = false;
                    break;
                case "N":
                    isUnresolved = false;
                    Ui.output("Exiting program... Restore or delete the data file");
                    break;
                default:
                    Ui.output("Please reply with Y/N only");
                    break;
                }
            }
            userInput.close();
        } else { // No error with reading data
            this.runJyuuni();
        }
    }

    /**
     * Runs chatbot.
     */
    private void runJyuuni() {
        boolean isRun = true;
        Scanner userInput = new Scanner(System.in);
        Ui.output("Hey its Jyuuni, your helpful assistant.\n     How can I help you?");

        // Requests user input until program is instructed to end.
        while (isRun) {
            String input = userInput.nextLine();
            isRun = Parser.parseCommand(input, taskList);
            storage.writeToFile(taskList);
        }
        userInput.close();
    }

    public static void main(String[] args) {
        new Duke("./data").initialise();
    }
}
