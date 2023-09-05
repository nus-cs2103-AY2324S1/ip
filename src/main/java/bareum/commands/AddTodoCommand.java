package bareum.commands;

import bareum.BareumException;
import bareum.TodoTask;
import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

/**
 * This class implements the command for creating a new todo.
 */
public class AddTodoCommand extends Command {
    /**
     * Description of the todo.
     */
    String description;

    /**
     * Create a new instance of a command that creates a todo with the corresponding description when executed.
     * @param description Description of the todo.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Create a new todo with the corresponding description.
     * @param ui Lets the user know if the creation of the todo was successful.
     * @param storage Saves the event to the hard disk after creating it.
     * @param taskList Task list to add the event to.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        try {
            TodoTask task = TodoTask.makeTodo(description);
            taskList.addTask(task);
            storage.saveNewTask(task);

            String added = "I have added this task:\n" + task + "\nYou now have "
                    + taskList.size() + " task(s) in your list.";
            Ui.reply(added);
        } catch (BareumException e) {
            Ui.reply("Oops! The description of a todo cannot be empty.\n" +
                    "Correct format: todo <description>");
        }
    }
}
