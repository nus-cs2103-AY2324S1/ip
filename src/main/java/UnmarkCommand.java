import java.io.File;
import java.util.List;
import java.util.Map;

public class UnmarkCommand extends Command {
  
  public UnmarkCommand(Map<String, Object> args) {
    super("unmark", args);
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
    tasks.unmark(idx);
    ui.print(String.format("OK, I've marked this task as not done yet:\n  %s", tasks.get(idx).toString()));
  }

  @Override
  public boolean isExit() {
    return false;
  }

}
