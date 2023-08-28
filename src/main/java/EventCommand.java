import java.util.List;
import java.util.Map;

public class EventCommand extends TaskCommand {
  
  public EventCommand(Map<String, Object> args) {
    super("event", args);
  }

  @Override
  public List<CommandElement> getCommandElements() {
    return List.of(new StringArgument("description"), new Field("/from"), new StringArgument("from"), new Field("/to"), new StringArgument("to"));
  }

  @Override
  public Task makeTask() {
    return new Event((String) this.args.get("description"), (String) this.args.get("from"), (String) this.args.get("to"));
  }

}
