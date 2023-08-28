import java.util.List;
import java.util.Map;

public class DeadlineCommand extends TaskCommand {
  
  public DeadlineCommand(Map<String, Object> args) {
    super("deadline", args);
  }

  @Override
  public List<CommandElement> getCommandElements() {
    return List.of(new StringArgument("description"), new Field("/by"), new StringArgument("by"));
  }

  @Override
  public Task makeTask() {
    return new Deadline((String) this.args.get("description"), (String) this.args.get("by"));
  }

}
