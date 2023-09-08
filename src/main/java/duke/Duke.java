package duke;

import java.time.format.DateTimeParseException;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import duke.command.Command;
import duke.task.TaskList;
/**
 * Duke is a chat-bot program, that can help you manage your tasks.
 * The chat-bot's name is Chewbacca, son of Attichukk.
 *
 * @author Lian Zhi Xuan
 */
public class Duke extends Application {
    private static Parser parser;
    private static TaskList list;
    private static Storage storage;

    public static void main(String[] args) {
        initialize();
        Ui.ui.startPrompt();
        run();
    }

    @Override
    public void start(Stage stage) {
        initialize();
        Ui.ui.GuiSetup(stage);
        Ui.ui.readInput();
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
            String input = Ui.ui.readInput();
            Command cmd = parser.readInput(input);
            return cmd.execute(list);

        } catch (DukeException e) {
            return Ui.ui.errorPrompt(e);

        } catch (DateTimeParseException e) {
            return Ui.ui.wrongDateFormatPrompt();
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
