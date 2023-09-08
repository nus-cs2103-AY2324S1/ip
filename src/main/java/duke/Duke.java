package duke;

import duke.command.Command;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidIndexException;
import duke.exception.NoSuchCommandException;
import duke.exception.UnmatchedArgumentException;
import duke.task.TaskList;

import java.time.DateTimeException;
import java.nio.file.Path;

public class Duke {

    /*
        The task list of Duke.
     */
    private TaskList tasks;
    /*
        The interface for interaction with user.
     */
    private Ui ui;
    /*
        The file that is responsible for reading, writing and saving of the tasks.
     */
    private Storage storage;
    /*
        A parser that is used to parse the input of the user.
     */
    private Parser parser;

    /**
     * Constructs a Duke object with the given file path.
     *
     * @param filePath The path of the file that contains the information of the tasks.
     */
    public Duke(Path filePath) {

        this.ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.processFile());
    }

    /**
     * Runs the Duke application to interact with the user until the exit command is entered.
     */
    public String getResponse(String input) {

        String result;
        boolean isExit = false;

        try {
            Command c = Parser.parse(input);
            result = c.execute(tasks, storage);
            if (c.isExit()) {
                result = "";
            }
        } catch (NoSuchCommandException e) {
            result = e.toString();
        } catch (InvalidIndexException e) {
            result = e.toString();
        } catch (UnmatchedArgumentException e) {
            result = e.toString();
        } catch (EmptyDescriptionException e) {
            result = e.toString();
        } catch (NumberFormatException | StringIndexOutOfBoundsException | DateTimeException e) {

            result = Ui.showLine() + "\n\tPlease enter a proper date." + "\n\t" + e.getMessage();
            result += "\n";
            result += Ui.showLine();
        }
        return result;
    }
}
