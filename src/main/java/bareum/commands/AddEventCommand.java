package bareum.commands;

import bareum.EventTask;
import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

public class AddEventCommand extends Command {
    private String description;
    private String startDateTime;
    private String endDateTime;

    public AddEventCommand(String description, String startDateTime,
                           String endDateTime) {
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        EventTask task = EventTask.makeEvent(this.description, this.startDateTime, this.endDateTime);
        taskList.addTask(task);
        storage.saveNewTask(task);
        String added = "I have added this task:\n" + task + "\nYou now have "
                + taskList.size() + " task(s) in your list.";
        Ui.reply(added);
    }
}
