package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.tasks.Event;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.ui.Ui;

public class EventCommand extends Command{
    String description;
    String from;
    String to;
    public EventCommand(String description, String from, String to){
        this.description = description;
        this.from = from;
        this.to = to;
    }
    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        Task event = new Event(description,from, to);
        tasksList.addTask(event);
        ui.showAddedTask(tasksList);
    }
}
