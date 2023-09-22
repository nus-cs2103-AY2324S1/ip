package chatbuddy.command;

import chatbuddy.ChatBuddyException;
import chatbuddy.TaskList;
import chatbuddy.storage.Storage;
import chatbuddy.task.ToDo;
import chatbuddy.ui.Ui;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        ToDo todo = new ToDo(taskDescription);
        tasks.addTask(todo);
        storage.save(tasks);
        return ui.showTaskAddition(todo, tasks.getSize());
    }
}
