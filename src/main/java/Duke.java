import duke.Parser;
import duke.Ui;
import duke.command.Command;
import duke.exceptions.InvalidTaskException;
import duke.Storage;
import duke.task.TaskList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    
    protected static Ui ui = new Ui();
    protected static TaskList taskList = new TaskList();
    protected static Parser parser = new Parser();
    protected static Storage storage = new Storage("./data/duke.txt");

    /**
     * Reads the user's input and executes a task based on it
     */
    public static void reply() {
        String response = ui.readCommand();
        Command command;
        while (!response.equals("bye")) {
            try {
                command = parser.parse(response);
                command.execute(storage, ui, taskList);
            } catch (InvalidTaskException e) {
                System.out.println(ui.format_response(e.getMessage()));
            } finally {
                response = ui.readCommand();
            }
        };
    }

    public static void main(String[] args) {
        ui.greet();
        reply();
        ui.bye();
    }
}
