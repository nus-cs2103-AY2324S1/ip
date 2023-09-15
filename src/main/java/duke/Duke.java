package duke;

import java.time.format.DateTimeParseException;



import duke.command.Command;
import duke.task.TaskList;
/**
 * Duke is a chat-bot program, that can help you manage your tasks.
 * The chat-bot's name is Chewbacca, son of Attichukk.
 *
 * @author Lian Zhi Xuan
 */
public class Duke{
    private static Parser parser;
    private static TaskList list;
    private static Storage storage;

    public static void main(String[] args) {
        initialize();
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
    public static String run() {
        try {
            String input = Ui.instance.getCurrInput();
            Command cmd = parser.readInput(input);
            return cmd.execute(list);

        } catch (DukeException e) {
            return Ui.instance.errorPrompt(e);

        } catch (DateTimeParseException e) {
            return Ui.instance.wrongDateFormatPrompt();
        }
    }

    /**
     * Reads current number of tasks, stored in list
     *
     * @return size of list
     */
    public static int getListSize() {
        return list.getList().size();
    }

    /**
     * Returns the list of task
     *
     * @return TaskList
     */
    public static TaskList getList() {
        return list;
    }

}
