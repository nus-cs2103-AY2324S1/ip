package Comm;
import Ui.Ui;
import Storage.TaskList;
import Storage.FileHandler;
import TaskManager.ToDos;
public class AddTodoCommand extends Command{
    private String userInput;

    public AddTodoCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList t, Ui ui, FileHandler f) {
        ToDos newtodo = new ToDos(userInput);
        if (newtodo.isValid()) {
            t.add(newtodo);
            f.writeTasksToFile(t);
            ui.addedTodo(newtodo);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
