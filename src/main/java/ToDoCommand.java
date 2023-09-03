import duke.exceptions.InvalidTaskException;
import java.util.regex.Matcher;
public class ToDoCommand extends Command {
    protected String description;
    protected boolean done;
    public ToDoCommand(String description, boolean done) {
        super();
        this.description = description;
        this.done = done;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        Task task = new ToDo(this.description);
        task.setDone(this.done);
        taskList.addTask(task);
        System.out.println(taskList);
    }
}
