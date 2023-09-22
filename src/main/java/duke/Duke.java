package duke;

import duke.Parser;
import duke.Ui;
import duke.command.Command;
import duke.exceptions.InvalidCommandException;
import duke.Storage;
import duke.exceptions.InvalidFileTypeException;
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
            String response = command.execute(storage, ui, taskList);
            storage.save(taskList);
            return response;
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }
    }

    public void load() throws InvalidFileTypeException {
        taskList.setTasks(storage.load());
    }
}
