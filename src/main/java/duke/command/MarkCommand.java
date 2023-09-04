package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import duke.ui.GobbleChatContainer;

/**
 * Represents a MarkCommand class that deals with the command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructor for MarkCommand.
     *
     * @param index of the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task as done and displays it as done.
     *
     * @param taskList list of tasks
     * @param ui       user interface
     * @param storage  storage
     */
    @Override
    public void execute(TaskList taskList, GobbleChatContainer chat, Storage storage) {
        taskList.markTask(index);
//        ui.showMarkMessage(taskList.getTask(index));
        chat.addMessage("Nice! I've marked this task as done:\n" + taskList.getTask(index), "Mark");
        
        storage.saveListToDisk(taskList.getTasks());
    }
}
