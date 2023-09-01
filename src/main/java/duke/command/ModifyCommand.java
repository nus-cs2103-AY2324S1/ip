package duke.command;

import duke.components.DukeException;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;

import duke.tasks.Task;

/**
 * Class of commands that modifies the existing list.
 */
public class ModifyCommand extends Command{
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
     * @param index The index of the list to modify.
     */
    public ModifyCommand(String type, int index) {
        this.type = type;
        this.index = index - 1;
    }

    /**
     * Executes the command based on user input.
     *
     * @param tasks List of tasks in taskList.
     * @param ui Instance of the user interface.
     * @param storage Instance of the storage.
     * @throws DukeException Invalid input.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (type.equals("L")) {
                ui.showList(tasks);
            } else {
                Task task = tasks.getTask(index);
                if (type.equals("M")) {
                    tasks.mark(index);
                    ui.showTaskCompleted(task);
                } else if (type.equals("U")) {
                    tasks.unmark(index);
                    ui.showTaskUnmarked(task);
                } else if (type.equals("D")) {
                    tasks.delete(index);
                    ui.showTaskDeleted(task, tasks.getSize());
                }
                storage.saveTasks(tasks.getTasks());
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
