package duke.command;

import duke.components.DukeException;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.tasks.Task;

/**
 * Class of commands that tags existing tasks in the list.
 */
public class TagCommand extends Command {
    /**
     * type contains the type of command.
     *
     * index is the index of the task to tag.
     *
     * tags contain the keyword to filter.
     */
    private String type;
    private int index;
    private String[] tags;

    /**
     * Constructor for the FilterCommand class.
     *
     * @param type The type of command.
     * @param index The index of the task.
     */
    public TagCommand(String type, int index, String[] tags) {
        this.type = type;
        this.index = index - 1;
        this.tags = tags;
    }


    /**
     * Executes the command from user input.
     *
     * @param tasks   List of tasks in taskList.
     * @param ui      Instance of the user interface.
     * @param storage Instance of the storage.
     * @return String representation of response to user input.
     * @throws DukeException If input is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            switch (type) {
            case "T": {
                Task task = tasks.getTask(index);
                task.setTags(tags);
                storage.saveTasks(tasks.getTasks());
                return ui.showTagsAdded(task);
            }
            case "U": {
                Task task = tasks.getTask(index);
                task.removeTags(tags);
                storage.saveTasks(tasks.getTasks());
                return ui.showTagsRemoved(task);
            }
            default:
                return null;
            }
        } catch (Exception ex) {
            throw new DukeException("I'm afraid I do not quite understand. Could you perhaps repeat yourself?");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
