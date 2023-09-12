package sam;

import sam.commands.Command;
import sam.commands.CommandResult;
import sam.services.Storage;
import sam.services.TaskList;
import sam.services.Ui;
import sam.services.parser.Parser;

public class Sam {

    private final String FILE_PATH = "./data/duke.txt";
    private TaskList taskList;
    private Storage storage;
    private Ui ui ;
    private Parser parser ;

    public Sam() {
        this.parser = new Parser();
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.taskList = new TaskList();
        try {
            storage.loadTasksFromFile(this.taskList);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    public CommandResult getResult(String input) {
        Command c = parser.parseCommand(input);
        if (c.isExit()) {
            return null;
        }
        return c.execute(taskList, ui, storage);
    }
}