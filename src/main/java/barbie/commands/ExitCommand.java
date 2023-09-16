package barbie.commands;

import java.util.ArrayList;

import barbie.Ui;
import barbie.types.Task;


public class ExitCommand extends Command {
    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public String run(ArrayList<Task> taskList) {
        return Ui.exit();

    }
}
