import DukeParsers.DukeParser;
import DukeStorage.DukeStorageDatabase;
import DukeTaskList.DukeTaskList;
import DukeUIClasses.DukeUI;

import java.io.IOException;

/**
 * This class encapsulates the nyanbot.
 *
 * @author Tan Kerway
 */
public class Duke {
    // ui class for normal ui displays to the user
    private final DukeUI ui;
    // storage class for db operations
    private final DukeStorageDatabase databaseController;
    // tasklist class for manipulating the tasklist
    private final DukeTaskList taskList;
    // parser class to parse input
    private final DukeParser parser;

    /**
     * Constructs an instance of a chatbot class.
     *
     * @author Tan Kerway
     * @throws IOException if the database is unable to be loaded
     */
    public Duke() throws IOException {
        this.ui = new DukeUI();
        this.taskList = new DukeTaskList();
        this.databaseController = new DukeStorageDatabase(taskList);
        this.parser = new DukeParser(this.taskList);

        this.taskList.setDatabaseController(this.databaseController);
        this.taskList.setParser(this.parser);
    }

    /**
     * Function that starts a chat with the user.
     *
     * @author Kerway
     */
    private void initiateChat() throws IOException {
        this.ui.greet(this.taskList.getTasks());           // warmly welcome the user
        this.parser.handleUserInput(); // take in the user input
        this.ui.sayGoodBye();      // say goodbye to the user
        this.databaseController.saveTaskList();    // save the user's task to the database
    }

    /**
     * This main function, when run, will cause the Chat Bot
     * to greet the user, and then exit.
     *
     * @author Tan Kerway
     * @param args pointer to some array of command-line arguments
     */
    public static void main(String[] args) {
        try {
            Duke dukeInstance = new Duke();
            dukeInstance.initiateChat();
        } catch (IOException e) {
            System.out.println("There was an issue accessing my nyanory :c");
        }
    }
}
