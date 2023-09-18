package duke;

import duke.Parser;
import duke.Ui;
import duke.command.Command;
import duke.exceptions.InvalidCommandException;
import duke.Storage;
import duke.task.TaskList;

public class Duke {
    
    protected static Ui ui = new Ui();
    protected static TaskList taskList = new TaskList();
    protected static Parser parser = new Parser();
    protected static Storage storage = new Storage("./data/duke.txt");

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command command;
        try {
            command = parser.parse(input);
            return command.execute(storage, ui, taskList);
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }
    }
}
