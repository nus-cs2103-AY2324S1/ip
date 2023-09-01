package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.tasks.Deadline;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.ui.Ui;
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
