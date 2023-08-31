package Comm;
import Ui.Ui;
import Storage.TaskList;
import Storage.FileHandler;
import TaskManager.Events;
public class AddEventCommand extends Command{
    private String userInput;
    private String to;
    private String from;
    public AddEventCommand(String userInput, String from, String to) {

        this.userInput = userInput;
        this.from = from;
        this.to = to;

    }

    @Override
    public void execute(TaskList task, Ui ui, FileHandler f) {

        Events newEvent = new Events(userInput, from, to);
        if (newEvent.isValid()) {

            task.add(newEvent);
            FileHandler.writeTasksToFile(task);
            ui.addedEvent(newEvent);

        }

    }

    @Override
    public boolean isExit() {

        return false;

    }
}