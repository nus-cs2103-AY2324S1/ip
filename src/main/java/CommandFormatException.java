import java.util.List;

public class CommandFormatException extends DukeException {

  public CommandFormatException(String cmd, String format) {
    super(String.format("☹ OOPS!!! %s command should have the following format:\n  %s", cmd, format));
  }
  
}
