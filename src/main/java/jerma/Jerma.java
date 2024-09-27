package jerma;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import jerma.commands.Command;
import jerma.utils.Parser;
import jerma.utils.Storage;
import jerma.utils.TaskList;
import jerma.utils.Ui;

/**
 * Jerma class, contains the chatbot
 */
public class Jerma {
    private TaskList tasks = new TaskList();
    private Ui ui = new Ui();
    private Boolean[] running = new Boolean[] { true };

    /**
     * Loads tasklist from file
     *
     * @return Response string
     */
    public String load() {
        try {
            tasks = Storage.load();
            return "Task list has been loaded";
        } catch (IOException e) {
            return ui.error("Save file not found");
        } catch (UnsupportedOperationException e) {
            return ui.error("Corrupted save file");
        }
    }

    public String hello() {
        return ui.hello();
    }

    /**
     * Parses the input and generates the bot's response.
     *
     * @param input The input string from the user
     * @return The bot's response
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input, ui, tasks, running);
            return command.execute();
        } catch (IndexOutOfBoundsException e) {
            return ui.error("Invalid arguments. Try again!");
        } catch (UnsupportedOperationException e) {
            return ui.error("Invalid command. Try again!");
        } catch (DateTimeParseException e) {
            return ui.error("Invalid date format. Try again!");
        }
    }
}
