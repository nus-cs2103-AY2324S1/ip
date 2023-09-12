package command;

import main.Main;
import main.Ui;
import task.TodoTask;

public class CommandTodoHandler implements ICommandHandler{

    @Override
    public void execute(String command, String[] parameters) throws CommandException {
        assert(Main.getInstance() != null);
        assert(Main.getInstance().getTaskList() != null);
        Main.getInstance().getTaskList().addTaskAndSay(new TodoTask(command.replaceFirst("todo ", "")));
    }
}
