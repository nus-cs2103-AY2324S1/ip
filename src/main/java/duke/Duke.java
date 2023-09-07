package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import duke.commands.Command;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.data.Message;


/**
 * Duke is an application that helps user store and manage tasks.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Message message;

    /**
     * Constructor to initialize Duke.
     *
     * @param filePath the path of the .txt file to be loaded
     */
    public Duke(String filePath) {
        message = new Message();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            message.showLoadingError();
            tasks = new TaskList();
        } catch (ParseException e) {
            message.showError(e.getMessage());
        }
    }

    public Duke() {
        message = new Message();
        try {
            storage = new Storage("data/tasks.txt");
            tasks = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            message.showLoadingError();
            tasks = new TaskList();
        } catch (ParseException e) {
            message.showError(e.getMessage());
        }
    }

    public String getResponse(String command) {
        try {
            Command c = Parser.parseCommand(command);
            return c.execute(tasks, message, storage);
        } catch (DukeException e) {
            return message.showError(e.getMessage());
        } catch (ParseException e) {
            return message.showInvalidFormat();
        } catch (IOException e) {
            return message.showWriteFileError();
        } catch (IllegalArgumentException exception) {
            return message.showInvalidCommand();
        }
    }
}
