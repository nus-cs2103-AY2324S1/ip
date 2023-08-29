package duke;

import duke.command.CommandBuilder;
import duke.exception.DukeException;
import duke.exception.DukeSideEffectException;
import duke.io.FileIO;
import duke.io.Printer;
import duke.task.Task;
import duke.task.TaskList;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {

  private final String BOT_NAME = "GOAT";
  private final String FILEPATH = "data/";
  private final String SAVEFILE_NAME = "save.txt";

  private final Scanner in = new Scanner(System.in);
  private final TaskList taskList = new TaskList();
  private final Printer out = new Printer();
  private final FileIO savefile = new FileIO(FILEPATH, SAVEFILE_NAME, "savefile");

  public static void main(String[] args) {
    (new Duke()).run();
  }

  public void run() {
    initialize();
    while (true) {
      String input = in.nextLine().trim();
      if (input.equals("bye")) {
        break;
      }
      CommandBuilder.parse(input).toCommand(out, taskList, savefile).execute();
    }
    sayBye();
  }

  void initialize() {
    String logo =
        "        ______    ___        _    _________ \n"
            + "      .' ___  | .'   `.     / \\  |  _   _  |\n"
            + "     / .'   \\_|/  .-.  \\   / _ \\ |_/ | | \\_|\n"
            + "     | |   ____| |   | |  / ___ \\    | |    \n"
            + "     \\ `.___]  \\  `-'  /_/ /   \\ \\_ _| |_   \n"
            + "      `._____.' `.___.'|____| |____|_____|  \n";

    out.print(String.format("Hello from\n%s", logo), String.format("Hello! I'm %s", BOT_NAME));

    try {
      readFromSaveFile();
    } catch (DukeSideEffectException e) {
      out.print(e);
    }
    out.flush();
  }

  void readFromSaveFile() {
    List<Task> tasks = new ArrayList<>();
    try {
      String input = savefile.read();
      if (input.equals("")) {
        out.print(String.format("Successfully read from savefile %s", savefile.getFilename()));
        return;
      }
      String[] lines = input.split("\n");
      Arrays.stream(lines)
          .forEach(
              (line) -> {
                CommandBuilder b = CommandBuilder.parse(line);
                tasks.add(Task.createTask(b.getCommand(), b.getName(), b.getArguments()));
              });
    } catch (FileNotFoundException e) {
      throw new DukeSideEffectException(
          String.format("Unable to open savefile %s", savefile.getFilename()));
    } catch (DukeException e) {
      throw new DukeSideEffectException(
          String.format("Savefile %s is not in the correct format", savefile.getFilename()));
    }

    out.print(String.format("Successfully read from savefile %s", savefile.getFilename()));
    taskList.populate(tasks);
  }

  void sayBye() {
    out.print("Bye. Hope to see you again soon!");
    out.flush();
  }
}
