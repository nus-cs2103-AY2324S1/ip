package joi;

import java.io.IOException;

import joi.loader.TaskLoader;
import joi.parser.TaskParser;
import joi.parser.Command;

import joi.utils.InvalidInputException;
import joi.utils.InvalidCommandException;
import joi.utils.TaskList;

import joi.ui.Ui;

public class Joi {
    private static final String NAME = "Joi";
    private boolean isRunning;
    private final TaskList taskList;
    private final Ui ui;
    private final TaskLoader taskLoader;

    // constructor for Duke
    public Joi() throws IOException {
        this.isRunning = true;
        this.ui = new Ui();
        this.taskLoader = new TaskLoader();
        this.taskList = this.taskLoader.loadTasks(this.ui);
    }

    // the event loop
    public void run() throws InvalidInputException, InvalidCommandException, IOException{
        this.ui.greeting(NAME);

        while (this.isRunning) {
            String input = this.ui.getUserInput();

            Command command = TaskParser.parseUserCommand(input);
            command.execute(this.ui, this.taskLoader, this.taskList);
            this.isRunning = command.getIsRunning();
            if (!this.isRunning) {
                break;
            }

            // store the taskList
            this.taskLoader.storeTasks(this.taskList);
        }
    }
}
