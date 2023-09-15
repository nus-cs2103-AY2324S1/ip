package barbie.commands;

import java.util.ArrayList;

import barbie.Ui;
import barbie.types.Task;

public class ListCommand extends Command{
    public ListCommand() {
        this.isExit = false;

    }

    @Override
    public String run(ArrayList<Task> taskList) {
        try {
            return Ui.listTasks(taskList);
        } catch (Exception e) {
            return e.getMessage();
        }
    }


}
