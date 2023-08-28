import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Duke {
  
  private static final String projectName = "ip";
  private static final File mainFile = new File(Duke.class.getProtectionDomain().getCodeSource().getLocation().getPath());
  private static final String name = "chatBot";
  private static final List<String> storagePath = List.of("data");
  private static final String storageFileName = "data.txt";
  private static final Map<String, Function<Map<String, Object>, Command>> commands = Map.ofEntries(
      new SimpleEntry<>("list", x -> new ListCommand(x)),
      new SimpleEntry<>("mark", x -> new MarkCommand(x)),
      new SimpleEntry<>("unmark", x -> new UnmarkCommand(x)),
      new SimpleEntry<>("todo", x -> new ToDoCommand(x)),
      new SimpleEntry<>("deadline", x -> new DeadlineCommand(x)),
      new SimpleEntry<>("event", x -> new EventCommand(x)),
      new SimpleEntry<>("remove", x -> new RemoveCommand(x)),
      new SimpleEntry<>("bye", x -> new ByeCommand(x))
    );

  public static void main(String[] args) {
    List<String> dirPath = new ArrayList<>(Arrays.asList(mainFile.getPath().split(Pattern.quote(File.separator))));
    while (!dirPath.get(dirPath.size() - 1).equals(projectName)) {
      dirPath.remove(dirPath.size() - 1);
    }
    List<Task> tasks = new ArrayList<>();
    File storage = makeFile(dirPath, storagePath, storageFileName);
    Ui ui;
    try {
      ui = new SilentUi(new Scanner(storage));
    } catch (FileNotFoundException e) {
      ui = new SilentUi(new Scanner(System.in));
    }
    while (ui.hasNext()) {
      try {
        String saved = ui.readCommand();
        if (saved.equals("")) {
          continue;
        }
        Command cmd = parseInput(saved);
        cmd.execute(tasks, ui, storage);
      } catch (DukeException e) {
        ui.print("☹ OOPS!!! Data file is corrupted. Starting from a clear state...");
        tasks.clear();
        break;
      }
    }
    ui.close();
    ui = new VerboseUi();
    ui.print(String.format("Hello! I'm %s\nWhat can I do for you?", name));
    boolean isExit = false;
    while (!isExit) {
      try {
        Command cmd = parseInput(ui.readCommand());
        cmd.execute(tasks, ui, storage);
        isExit = cmd.isExit();
      } catch (DukeException e) {
        ui.print(e.getMessage());
      }
    }
    ui.close();
  }
  
  private static Command parseInput(String input) throws DukeException {
    String[] commandParts = input.split(" ", 2);
    String commandName = commandParts[0];
    String commandArgs = commandParts.length > 1 ? commandParts[1] : "";
    Map<String, Object> args = new HashMap<>();
    if (!commands.containsKey(commandName)) {
      throw new InvalidCommandException(commandName);
    }
    Command cmd = commands.get(commandName).apply(args);
    Matcher groups = cmd.getPattern().matcher(commandArgs);
    if (!groups.matches()) {
      throw new CommandFormatException(commandName, cmd.getStructure());
    }
    List<Argument> fields = cmd.getArguments();
    for (int i = 0; i < fields.size(); i++) {
      Argument arg = fields.get(i);
      if (arg != null) {
        args.put(arg.toString(), arg.formatInput(groups.group(i)));
      }
    }
    return cmd;
  }

  private static File makeFile(List<String> projectDir, List<String> path, String fileName) {
    String dirPath = String.join(File.separator, projectDir) + File.separator + String.join(File.separator, path);
    File dir = new File(dirPath);
    if (!dir.exists()) {
      dir.mkdirs();
    }
    File opFile = new File(dirPath + File.separator + fileName);
    try {
      opFile.createNewFile();
    } catch (IOException e) {}
    return opFile;
  }

}
