package commands;

import storage.Storage;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class DeadlineCommand extends Command{
    String description;
    String date;
    public DeadlineCommand(String description, String date){
        this.description = description;
        this.date = date;
    }
    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        Task deadline = new Deadline(description,date);
        tasksList.addTask(deadline);
        ui.showAddedTask(tasksList);
    }
}
