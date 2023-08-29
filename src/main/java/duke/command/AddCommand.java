package duke.command;
import duke.task.*;
import duke.helper.*;
import java.io.IOException;

public class AddCommand extends Command{

    Task task;



    public AddCommand(String command) throws DukeException {
        try {
            int firstIndexSpace = command.indexOf(" ");
            String taskType = command.substring(0,firstIndexSpace);
            String taskName = command.substring(firstIndexSpace + 1);

            if (taskType.equals("event")) task = new Event(taskName.trim());
            else if (taskType.equals("deadline")) task = new Deadline(taskName.trim());
            else if (taskType.equals("todo")) task = new Todo(taskName.trim());
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(" No Description given!");
        }

    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        try {
            tasks.addTask(task);
            store.save(tasks);
        } catch (IOException e) {
            throw new DukeException(" umable to locate local file!");
        }

    }
}
