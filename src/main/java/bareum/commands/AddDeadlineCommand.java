package bareum.commands;

import bareum.BareumException;
import bareum.DeadlineTask;
import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

public class AddDeadlineCommand extends Command {
    private String description;
    private String dueDate;


    public AddDeadlineCommand(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws BareumException {
        try {
            DeadlineTask task = DeadlineTask.makeDeadline(this.description, this.dueDate);
            taskList.addTask(task);
            storage.saveNewTask(task);
            String added = "I have added this task:\n" + task + "\nYou now have "
                    + taskList.size() + " task(s) in your list.";
            Ui.reply(added);
        } catch (BareumException e) {
            Ui.reply(e.getMessage());
        }
    }
}
