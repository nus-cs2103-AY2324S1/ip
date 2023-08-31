package Comm;
import TaskManager.Deadlines;
import Ui.Ui;
import Storage.TaskList;
import Storage.FileHandler;
public class AddDeadlineCommand extends Command{
    private String userInput;
    private String dueDateStr;

    public AddDeadlineCommand(String userInput, String dueDateStr) {
        this.dueDateStr = dueDateStr;
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList task, Ui ui, FileHandler f) {
        Deadlines newdeadlines = new Deadlines(userInput, dueDateStr);
        if (newdeadlines.isValid()) {
            task.add(newdeadlines);
            FileHandler.writeTasksToFile(task);
            ui.addedDeadlines(newdeadlines);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}