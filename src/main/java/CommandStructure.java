import java.util.Map;
import java.util.HashMap;

public class CommandStructure {
	public String command;
	public String name;
	public Map<String, String> arguments;

	public CommandStructure(String command, String name, Map<String, String> arguments) {
		this.command = command;
		this.name = name;
		this.arguments = arguments;
	}

  public static CommandStructure parse(String line) {
    String[] input = line.split(" ", 2);
    String command = input[0];
    String name = "";
    Map<String, String> arguments = new HashMap<>();

    if (input.length == 2) {
      String[] arr = input[1].split("/");
      name = arr[0].trim();
      for (int i = 1; i < arr.length; ++i) {
        String[] argument = arr[i].split(" ", 2);
        String value = "";
        if (argument.length == 2) {
          value = argument[1].trim();
        }
        arguments.put(argument[0].trim(), value);
      }
    }

    return new CommandStructure(command, name, arguments);
  }

}
