package duke;

import java.util.ArrayList;
import java.io.IOException;
import duke.exception.DukeException;
import duke.task.CommandEnum;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Encapsulates an instance of a chatbot program.
 */
public class Duke {
    private final String line = "____________________________________________________________";
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String output = this.run(input);
        return output;
    }

    /**
     * Constructor method for Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();

        try {
            ArrayList<Task> loadedTasks = this.storage.loadTasks();
            this.tasks = new TaskList(loadedTasks);
        } catch (DukeException e) {
            this.ui.explainException(e);
        }
    }

    private String run(String input) {
        String nextTask = input;
        CommandEnum taskEnum = CommandEnum.assignEnum(nextTask);

        switch (taskEnum) {
        case LIST:
            return this.tasks.printTasks();
        case MARK:
        case UNMARK:
            try {
                return this.tasks.handleMark(nextTask);
            } catch (DukeException e) {
                return this.ui.explainException(e);
            }
        case TODO:
        case DEADLINE:
        case EVENT:
            try {
                return this.tasks.handleTask(nextTask);
            } catch (DukeException e) {
                return this.ui.explainException(e);
            }
        case DELETE:
            try {
                return this.tasks.handleDelete(nextTask);
            } catch (DukeException e) {
                return this.ui.explainException(e);
            }
        case FIND:
            try {
                return this.tasks.find(nextTask);
            } catch (DukeException e) {
                return this.ui.explainException(e);
            }
        case BYE:
            try {
                this.storage.saveTasks(this.tasks.getList());
            } catch (IOException e) {
                String output = "Unable to save your tasks to a file. Try Again.";
                return output;
            }
            return this.ui.bye();
        default:
            return this.ui.handleInvalid();
        }
    }
}
