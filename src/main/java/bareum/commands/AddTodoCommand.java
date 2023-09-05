package bareum.commands;

import bareum.BareumException;
import bareum.TodoTask;
import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

public class AddTodoCommand extends Command {
    String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

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
