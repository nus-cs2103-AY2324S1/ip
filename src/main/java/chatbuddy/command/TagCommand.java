package chatbuddy.command;

import chatbuddy.ChatBuddyException;
import chatbuddy.TaskList;
import chatbuddy.storage.Storage;
import chatbuddy.task.Task;
import chatbuddy.ui.Ui;

/** TagCommand represents a command to change the tag of a task. */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";
    private int taskNum;
    private String tag;

    /**
     * Creates an instance of a TagCommand with the given task number and tag.
     *
     * @param taskNum The task number of the task to change the tag.
     * @param tag The tag of the task.
     */
    public TagCommand(int taskNum, String tag) {
        this.taskNum = taskNum;
        this.tag = tag;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        Task task = tasks.updateTaskTag(taskNum, tag);
        storage.save(tasks);
        return ui.showUpdatedTag(task);
    }
}
