package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.GobbleChatContainer;
import javafx.scene.layout.VBox;

/**
 * Represents a AddCommand class that deals with the command to add a task.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructor for AddCommand.
     *
     * @param task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a task to the list.
     *
     * @param taskList list of tasks.
     * @param chat     chat box user interface.
     * @param storage  storage.
     */
    @Override
    public void execute(TaskList taskList, GobbleChatContainer chat, Storage storage) {
        taskList.addTask(task);
//        chat.showAddTaskMessage(task, taskList.getSize());
        chat.addMessage("Got it. I've added this task:\n" + task + "\nNow you have " + taskList.getSize() + " tasks in the list.", "Add");
        storage.saveListToDisk(taskList.getTasks());
    }
}
