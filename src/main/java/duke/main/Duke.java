package duke.main;

import java.time.DateTimeException;

import duke.command.Command;
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

    private static TaskList inputList;
    private static Parser parser = new Parser();
    private static Storage storage = new Storage();

    String getResponse(String userInput) {
        inputList = storage.loadData();
        String input = userInput.trim();
        String[] inp = input.split("\\s+");
        try {
            Command currentCommand = parser.parse(inp, inputList);
            return currentCommand.execute(inputList, storage);
        } catch (DukeException e) {
            return "JonBird:\n\t" + e;
        } catch (DateTimeException e) {
            return "JonBird:\n\t" + "Please ensure that your date is in \"yyyy-MM-dd HH:mm\""
                    + " format. Put 00:00 if time does not matter.";
        }
    }
}
