package didier;

import didier.command.Command;
import didier.exception.DidierException;

/**
 * Represents a Didier bot that is able to interact with a user, keep track of the list of tasks
 * of the user and save the data in local storage.
 */
public class Didier {

    private Storage storage;
    private TaskList taskList;
    private boolean isActive;

    /**
     * The constructor for the Didier bot.
     *
     * @param directoryPath The path to the directory where Didier should store the task list.
     * @param fileName The name of the file where Didier should store the task list.
     */
    public Didier(String directoryPath, String fileName) {
        storage = new Storage(directoryPath, fileName);
        taskList = storage.getTasks();
        isActive = true;
    }

    /**
     * Returns the response of the didier bot based on the commandString inputted by the user.
     *
     * @param commandString The user input.
     * @return The response of the didier bot.
     */
    public String getResponse(String commandString) {
        try {
            Command command = Parser.parse(commandString);
            if (command.isExit()) {
                isActive = false;
            }
            command.execute(this.taskList, this.storage);
            return command.getBotOutput(this.taskList, this.storage);
        } catch (DidierException exception) {
            return exception.getMessage() + "Please try again.";
        }
    }

    /**
     * Returns the bot uses to greet the user at the start of the user-bot interaction.
     * @return The greeting message.
     */
    public static String getBotGreeting() {
        return "Greetings user, I'm didier. How can I help you?";
    }

    /**
     * Returns whether the bot is currently active or not.
     * @return The status of the bot.
     */
    public boolean getIsActive() {
        return this.isActive;
    }
}
