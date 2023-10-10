package rua.command;

import java.util.ArrayList;

import rua.common.Storage;
import rua.common.Ui;
import rua.exception.InvalidCommandException;
import rua.task.Task;
import rua.task.TaskList;

/**
 * Represents Command for tagging related operation (add, delete, clear).
 */
public class TagCommand implements Command {
    private final ArrayList<String> tags;
    private final int index;
    private final String command;

    static final String MESSAGE_DELETE = "Successfully delete all required tags:\n";
    static final String MESSAGE_ADD = "Successfully add all required tags:\n";
    static final String MESSAGE_CLEAR = "Successfully clear all tags:\n";

    /**
     * Constructs a TagCommand.
     *
     * @param index The index of task to be edited.
     * @param tags The new list of tags.
     * @param command A string to indicate the type of the command.
     */
    public TagCommand(int index, ArrayList<String> tags, String command) {
        this.index = index;
        this.tags = tags;
        this.command = command;
    }

    /**
     * {@inheritDoc}
     *
     * @return The exit status after this execution.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Sets a Task at given index to be marked/unmarked and returns the updated TaskList.
     *
     * @param tasks Current TaskList.
     * @param ui A UI to show messages to the user.
     * @param storage A Storage to save and load tasks
     * @return The updated TaskList after marking/unmarking the Task.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        assert index < tasks.getTasks().size() : "We don't have so many tasks";
        Task targetTask = tasks.getTasks().get(index - 1);
        String message;
        switch (command) {
        case "add":
            targetTask.addTags(tags);
            message = MESSAGE_ADD;
            break;
        case "delete":
            targetTask.deleteTags(tags);
            message = MESSAGE_DELETE;
            break;
        case "clear":
            targetTask.clearTags();
            message = MESSAGE_CLEAR;
            break;
        default:
            throw new InvalidCommandException();
        }
        ui.showMessage(message);
        ui.showMessage("    " + targetTask);
        storage.save(tasks);
        return tasks.updateTask(index - 1, targetTask);
    }

    /**
     * {@inheritDoc}
     *
     * @param o Another object to be compared with.
     * @return A boolean indicating whether they are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof TagCommand)) {
            return false;
        }

        TagCommand c = (TagCommand) o;
        return c.tags.equals(this.tags) && c.index == this.index && c.command.equals(this.command);
    }
}
