import java.io.File;
import java.util.List;
import java.util.Map;

public class RemoveCommand extends Command {
  
  public RemoveCommand(Map<String, Object> args) {
    super("remove", args);
  }

  @Override
  public List<CommandElement> getCommandElements() {
    return List.of(new IndexArgument("index"));
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    int idx = (int) ((Integer) this.args.get("index"));
    if (idx >= tasks.size()) {
      throw new OutOfBoundsException(idx, tasks.size());
    }
    ui.print(String.format("Noted. I've removed this task:\n  %s\n%s", tasks.remove(idx).toString(), ui.getTaskCount(tasks.size())));
  }

  @Override
  public boolean isExit() {
    return false;
  }

}
