package duke;

import duke.exceptions.DukeException;
import duke.tools.KeywordEnum;
import duke.tools.Ui;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tasks.Task;

import java.util.ArrayList;

public class Duke {

    private Storage storage = new Storage();
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        this.ui = new Ui();
        this.storage.read();
        ArrayList<Task> tasks = this.storage.getTaskList();
        this.taskList = new TaskList(tasks);
    }

    public String getResponse(String input) {
        String output = this.handleInput(input);
        return output;
    }

    public String handleInput(String task) {
        KeywordEnum keywordEnum = KeywordEnum.assign(task);

        switch(keywordEnum) {
            case LIST:
                try {
                    return this.taskList.printList();
                } catch (DukeException e) {
                    return e.getMessage();
                }
            case BYE:
                this.storage.saveTasks(this.taskList.getTaskArray());
                return this.ui.printOutro();
            case TODO:
                return this.taskList.handleTodo(task);
            case DEADLINE:
                return this.taskList.handleDeadline(task);
            case EVENT:
                return this.taskList.handleEvent(task);
            case DELETE:
                try {
                    return this.taskList.delete(task);
                } catch (DukeException e) {
                    return e.getMessage();
                }
            case MARK:
            case UNMARK:
                try {
                    return this.taskList.mark(task);
                } catch (IndexOutOfBoundsException e) {
                    return e.getMessage();
                } catch (DukeException dukeException) {
                    return dukeException.getMessage();
                }
            case FIND:
                try {
                    return this.taskList.find(task);
                } catch (DukeException e) {
                    return e.getMessage();
                }
            default:
                return "This is not a valid task.";
        }
    }
}
