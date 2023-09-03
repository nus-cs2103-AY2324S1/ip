import duke.exceptions.InvalidTaskException;
import java.util.regex.Matcher;
public class EventCommand extends Command {
    protected String description;
    protected String from;
    protected String to;
    protected boolean done;
    public EventCommand(String description, String from, String to, boolean done) {
        super();
        this.description = description;
        this.from = from;
        this.to = to;
        this.done = done;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        Task task = new Event(description, from, to);
        task.setDone(done);
        taskList.addTask(task);
        System.out.println(taskList);
    }

}
