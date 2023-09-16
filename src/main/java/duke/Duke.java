package duke;


import command.Command;
import dukeexception.DukeException;
import filestorage.FileStorage;
import list.TaskList;
import parser.Parser;
import ui.Ui;

/**
 * The main program of the chat_bot.
 * Works with the MainWindow to provide inputs for GUI
 */
public class Duke {

    private TaskList userList;
    private final FileStorage fileStorage;
    private final Ui userInterface;

    /**
     * Initialises the Chat_Bot.
     *
     * @param filePath The file that will be written or read from.
     */
    public Duke(String filePath) {
        this.userInterface = new Ui();
        this.fileStorage = new FileStorage(filePath);
        try {
            userList = new TaskList(fileStorage.read());
        } catch (DukeException e) {
            userList = new TaskList();
            System.out.println("File Empty");
        }
    }

    /**
     * Starts the class up.
     *
     * @param args The arguments use to start the program.
     */
    public static void main(String[] args) {
        new Duke("Testing.txt").run();
    }

    /**
     * Runs the UserInterface inputs and provide outputs.
     */
    public void run() {
        userInterface.showGreetings();
        boolean isCmdExit = false;
        while (!isCmdExit) {
            try {
                String fullCommand = userInterface.readCommand();
                userInterface.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(userList, userInterface, fileStorage);
                isCmdExit = c.isExit();
            } catch (DukeException e) {
                userInterface.showError(e.getMessage());
            } finally {
                userInterface.showLine();
            }
        }
        userInterface.closeScanner();
    }

    /**
     * Gets the response from the chat box inputs.
     *
     * @param input The input users put in.
     * @return A String output given out dependent on the input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(userList, userInterface, fileStorage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Runs the greeting of the Chat Bot.
     *
     * @return A String that shows greetings.
     */
    public String start() {
        return userInterface.showGreetings() + "\n Enter clear if you would like to start a new list";
    }
}

