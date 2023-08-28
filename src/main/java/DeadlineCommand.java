import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DeadlineCommand extends TaskCommand {
  
  public DeadlineCommand(Map<String, Object> args) {
    super("deadline", args);
  }

  @Override
  public List<CommandElement> getCommandElements() {
    return List.of(new StringArgument("description"), new Field("/by"), new DateArgument("by"));
  }

  @Override
  public Task makeTask() {
    return new Deadline((String) this.args.get("description"), (LocalDate) this.args.get("by"));
  }

}
