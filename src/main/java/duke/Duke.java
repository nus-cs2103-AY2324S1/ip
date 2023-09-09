package duke;

import command.Command;
import exception.DukeException;
import exception.EmptyDateTimeException;
import exception.EmptyInputException;
import exception.InvalidCommandException;
import exception.InvalidDateTimeException;
import exception.InvalidFormatException;

/**
 * Duke is the chatbot program.
 *
 * @author fry
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * The constructor of Duke.
     *
     * @param filePath The file path to be passed into to load the initial tasks.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.load());
        this.parser = new Parser(this.taskList, this.ui, this.storage);

    }

    /**
     * Generate a response to user input.
     *
     * @param input The input of the user.
     * @return The response of paimonbot.
     */
    public String getResponse(String input) {
        String response;
        ui.readCommand(input);
        Command c = parser.parse(input);
        try {
            response = c.execute(taskList, ui, storage);
        } catch (EmptyInputException e) {
            response = e.getMessage();
        } catch (EmptyDateTimeException e) {
            response = e.getMessage();
        } catch (InvalidFormatException e) {
            response = e.getMessage();
        } catch (InvalidCommandException e) {
            response = e.getMessage();
        } catch (InvalidDateTimeException e) {
            response = e.getMessage();
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }
}

