package duke;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.command.Command;
import duke.task.TaskList;

import java.time.format.DateTimeParseException;

/**
 * Duke is a chat-bot program, that can help you manage your tasks.
 * The chat-bot's name is Chewbacca, son of Attichukk.
 *
 * @author Lian Zhi Xuan
 */
public class Duke {

    private static TaskList list;
    public static Parser parser;
    private static Storage storage;



    public static void main(String[] args) {
        initialize();
        Ui.ui.startPrompt();
        run();
    }

    /**
     * Initializes all required objects to run Duke program
     */
    public static void initialize() {
        storage = new Storage();
        list = new TaskList();
        storage.load(list);

        parser = new Parser();
    }

    /**
     * Runs the actual program.
     */
    public static void run() {
        try {
            String input = Ui.ui.readInput();
            Command cmd = parser.readInput(input);
            cmd.execute(list);

        } catch (DukeException e) {
            Ui.ui.errorPrompt(e);
            run();

        } catch (DateTimeParseException e) {
            Ui.ui.wrongDateFormatPrompt();
            run();
        }
    }

    /**
     * Reads current number of tasks, stored in list
     *
     * @return size of list
     */
    public static int listSize() {
        return list.list().size();
    }

    /**
     * Returns the list of task
     *
     * @return TaskList
     */
    public static TaskList list() {
        return list;
    }

}
