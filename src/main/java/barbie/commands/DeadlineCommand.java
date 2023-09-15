package barbie.commands;

import java.time.LocalDate;
import java.util.ArrayList;

import barbie.Storage;
import barbie.Ui;
import barbie.types.Deadlines;
import barbie.types.Task;


public class DeadlineCommand extends Command {
    String desc;
    LocalDate by;

    public DeadlineCommand(String desc, LocalDate by) {
        this.desc = desc;
        this.by = by;
        this.isExit = false;

    }

    @Override
    public String run(ArrayList<Task> taskList) {
        Deadlines deadline = new Deadlines(this.desc,  this.by);
        taskList.add(deadline);
        Storage.addToList(desc, by);
        return Ui.taskAdded(deadline);

    }

}
