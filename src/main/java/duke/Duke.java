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

        try {
            Command c = Parser.parse(input, this.tasks.size());
            result = c.execute(tasks, storage);
            System.out.println(result);
            assert result.length() > 0 : "There will always be a return value.";
        } catch (NoSuchCommandException e) {
            result = e.toString();
        } catch (InvalidIndexException | NumberFormatException e) {

            result = "Please enter a valid index, e.g. a number in the list.";
            result += "\n";
        } catch (UnmatchedArgumentException e) {
            result = e.toString();
        } catch (EmptyDescriptionException e) {
            result = e.toString();
        } catch (StringIndexOutOfBoundsException | DateTimeException e) {

            result = "Please enter a proper date." + "\n\t" + e.getMessage();
            result += "\n";
        }
        return result;
    }
}
