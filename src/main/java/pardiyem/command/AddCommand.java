package pardiyem.command;

import java.io.IOException;
import java.util.ArrayList;

import pardiyem.storage.Storage;
import pardiyem.task.Deadline;
import pardiyem.task.Event;
import pardiyem.task.Task;
import pardiyem.task.TaskList;
import pardiyem.task.Todo;
import pardiyem.ui.Ui;

public class AddCommand extends Command {
    private Task toAdd;

    public AddCommand(String desc, int type) {
        super(desc);
        
        switch (type) {
        case 1:
            toAdd = new Todo(desc);
            break;
        case 2: {
            ArrayList<String> args = Deadline.parseDesc(desc);
            toAdd = new Deadline(args.get(0), args.get(1));
            break;
        }
        case 3: {
            ArrayList<String> args = Event.parseDesc(desc);
            toAdd = new Event(args.get(0), args.get(1), args.get(2));
            break;
        }
        }
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.add(toAdd);
        ui.showOutput(String.format("Got it. I've added this task:\n%s\nNow you have %d task(s) in the list",
                toAdd.toString(), taskList.size()));
        storage.save(taskList);
    };
}
