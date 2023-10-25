package duke.command;

import duke.components.DukeException;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.tasks.Task;

/**
 * Class of commands that modifies the existing list.
 */
public class ModifyCommand extends Command {
    /**
     * type contains the type of command.
     *
     * index contains the index of the list to modify.
     *
     * isExit contains whether to terminate the bot.
     */
    private String type;
    private int index;
    private boolean isExit = false;

    /**
     * Constructor for ModifyCommand.
     *
     * @param type The type of command.
     */
    public ModifyCommand(String type) {
        this.type = type;
    }

    /**
     * Constructor for ModifyCommand.
     *
     * @param type The type of command.
     * @param index The index of the list to modify.
     */
    public ModifyCommand(String type, int index) {
        this.type = type;
        this.index = index - 1;
    }

    /**
     * Executes the command based on user input.
     *
     * @param tasks   List of tasks in taskList.
     * @param ui      Instance of the user interface.
     * @param storage Instance of the storage.
     * @return String in response to user input.
     * @throws DukeException Invalid input.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.getTask(index);
            switch (type) {
            case "L":
                return ui.showList(tasks);
            case "M":
                tasks.mark(index);
                storage.saveTasks(tasks.getTasks());
                return ui.showTaskCompleted(task);
            case "U":
                tasks.unmark(index);
                storage.saveTasks(tasks.getTasks());
                return ui.showTaskUnmarked(task);
            case "D":
                tasks.delete(index);
                storage.saveTasks(tasks.getTasks());
                return ui.showTaskDeleted(task, tasks.getSize());
            default:
                return null;
            }
        } catch (Exception ex) {
            throw new DukeException("I'm afraid such a task do not exist.");
        }
    }

    @Override
    public boolean isExit() {
        return isExit;
    }

    public String getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }
}
