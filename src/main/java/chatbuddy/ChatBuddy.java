package chatbuddy;

import chatbuddy.command.Command;
import chatbuddy.command.ExitCommand;
import chatbuddy.parser.Parser;
import chatbuddy.storage.Storage;
import chatbuddy.ui.Ui;

/** ChatBuddy is a chatbot that helps a user keep track of tasks. */
public class ChatBuddy {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates an instance of ChatBuddy with a specified filepath.
     *
     * @param filePath The filepath of the file to save task data in.
     */
    public ChatBuddy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChatBuddyException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns the welcome message.
     *
     * @return The welcome message.
     */
    public String getWelcome() {
        return ui.showWelcome();
    }

    /**
     * Saves the data and returns the exit message.
     *
     * @return The exit message.
     */
    public String saveAndGetExitMessage() {
        try {
            ExitCommand exitCommand = new ExitCommand();
            return exitCommand.execute(tasks, ui, storage);
        } catch (ChatBuddyException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the response of the command executed from the input.
     * @param input The user input.
     * @return The response of the command.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (ChatBuddyException e) {
            return e.getMessage();
        }
    }
}
