import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ByeCommand extends Command {
  
  public ByeCommand(Map<String, Object> args) {
    super("bye", args);
  }

  @Override
  public List<CommandElement> getCommandElements() {
    return List.of();
  }

  @Override
  public void execute(List<Task> tasks, Ui ui, File storage) {
    try {
      FileWriter writer = new FileWriter(storage, false);
      for (int i = 0; i < tasks.size(); i++) {
        writer.append(tasks.get(i).toCommand(i));
      }
      writer.flush();
      writer.close();
    } catch (IOException e) {}
    ui.print("Bye. Hope to see you again soon!");
  }

  @Override
  public boolean isExit() {
    return true;
  }

}