package chatbuddy.command;

import chatbuddy.ChatBuddyException;
import chatbuddy.storage.Storage;
import chatbuddy.TaskList;
import chatbuddy.ui.Ui;
import chatbuddy.task.ToDo;

/** TodoCommand represents a command to create a todo task. */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    private String taskDescription;

    /**
     * Creates an instance of a TodoCommand with the given description.
     *
     * @param taskDescription The description of the task.
     */
    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        ToDo todo = new ToDo(taskDescription);
        tasks.addTask(todo);
        ui.showTaskAddition(todo, tasks.getSize());
    }
}
