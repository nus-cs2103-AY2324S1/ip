package duke;

import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 * Parses the command given by the user and returns a corresponding action.
 */
public class CommandParser {

  /**
   * Creates a parser to parse input commands
   */
  public CommandParser() {}

  private TimeParser parser = new TimeParser();

  private Task createTodoTask(String[] words) {
    StringBuilder taskName = new StringBuilder();
    for (int i = 1; i < words.length; i += 1) {
      taskName.append(words[i]).append(" ");
    }
    return new ToDoTask(taskName.toString().stripTrailing());
  }

  private Task createEventTask(String[] words) throws InvalidDateException {
    StringBuilder taskName = new StringBuilder();
    StringBuilder startDate = new StringBuilder();
    StringBuilder endDate = new StringBuilder();

    int i = 1;
    while (i < words.length && !words[i].equals("/from")) {
      taskName.append(words[i]).append(" ");
      i += 1;
    }
    i += 1;
    while (i < words.length && !words[i].equals("/to")) {
      startDate.append(words[i]).append(" ");
      i += 1;
    }
    i += 1;
    while (i < words.length) {
      endDate.append(words[i]).append(" ");
      i += 1;
    }

    return new EventTask(taskName.toString().stripTrailing(),
            parser.parseTime(startDate.toString().trim()), parser.parseTime(endDate.toString().trim()));
  }

  private Task createDeadlineTask(String[] words) throws InvalidDateException {
    StringBuilder taskName = new StringBuilder();
    StringBuilder endDate = new StringBuilder();

    int i = 1;
    while (i < words.length && !words[i].equals("/by")) {
      taskName.append(words[i]).append(" ");
      i += 1;
    }
    i += 1;
    while (i < words.length) {
      endDate.append(words[i]).append(" ");
      i += 1;
    }
    return new DeadlineTask(taskName.toString().stripTrailing(), parser.parseTime(endDate.toString().trim()));
  }

  private Task createTask(String[] words) throws EmptyBodyException, InvalidDateException {
    if (words.length == 1) {
      throw new EmptyBodyException();
    }

    if (words[0].equals("todo")) {
      return this.createTodoTask(words);
    } else if (words[0].equals("event")) {
      return this.createEventTask(words);
    } else {
      return this.createDeadlineTask(words);
    }
  }

  /**
   *  Checks the first word of the input string and returns an Action.
   *
   * @param command input given by the user
   * @return action an action to be executed depending on the first word of command
   * @throws DukeException if input string is invalid
   */
  public Action parseCommand(String command, Image dukeImage) throws DukeException {
    String[] words = command.trim().split("\\s");
    if (words[0].equals("bye") && words.length == 1) {
      return (taskList, storage, pane) -> {
        Platform.exit();
      };
    } else if (words[0].equals("list") && words.length == 1) {
      return (taskList, Storage, vBox) -> {
        taskList.listTasks(vBox, dukeImage);
      };
    } else if (words[0].equals("delete") && words.length == 2) {
      return (taskList, storage, pane) -> {
        taskList.deleteTask(words[1], pane, dukeImage);
        storage.saveFile(taskList);
      };
    } else if (words[0].equals("mark") && words.length == 2) {
      return (taskList, storage, pane) -> {
        taskList.markTask(words[1], pane, dukeImage);
        storage.saveFile(taskList);
      };
    } else if (words[0].equals("unmark") && words.length == 2) {
      return (taskList, storage, pane) -> {
        taskList.unmarkedTask(words[1], pane, dukeImage);
        storage.saveFile(taskList);
      };
    } else if ((words[0].equals("deadline") || words[0].equals("todo") || words[0].equals("event"))) {
      return (taskList, storage, pane) -> {
        taskList.addTask(this.createTask(words), pane, dukeImage);
        storage.saveFile(taskList);
      };
    } else if (words[0].equals("find")) {
      String expr = command.trim().substring(4).trim();
      return (taskList, storage, pane) -> {
        taskList.findTasks(expr, pane, dukeImage);
      };
    } else {
      throw new InvalidCommandException();
    }
  }
}
