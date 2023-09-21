package duke.main;

import java.time.DateTimeException;

import duke.command.Command;
import duke.command.CommandList;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * A class for the Duke bot.
 */
public class Duke {

    /**
     * The ui for Duke bot
     */
    private static final Ui ui = new Ui();

    /**
     * The taskList of the users
     */
    private static TaskList inputList;

    /**
     * The commandList of the users
     */
    private static CommandList commandList;

    /**
     * The parser to parse the user input
     */
    private static Parser parser = new Parser();

    /**
     * The storage to handle saving and loading of data from file
     */
    private static Storage storage = new Storage();

    /**
     * Returns the response to user input
     * @param userInput The input from user
     * @return The response to user input
     */
    String getResponse(String userInput) {
        inputList = storage.loadData();
        commandList = storage.previousCommandsLoader();
        String input = userInput.trim();
        String[] inp = input.split("\\s+");
        try {
            Command currentCommand = parser.parse(inp, inputList);
            return currentCommand.execute(inputList, storage, commandList, true);
        } catch (DukeException e) {
            return "JonBird:\n\t" + e;
        } catch (DateTimeException e) {
            return "JonBird:\n\t" + "Please ensure that your date is in \"yyyy-MM-dd HH:mm\""
                    + " format. Put 00:00 if time does not matter.";
        }
    }
}
