package duke;

public class CommandParser {

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

    return new EventTask(taskName.toString().stripTrailing(), parser.parseTime(startDate.toString().trim()), parser.parseTime(endDate.toString().trim()));
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

  public Action parseCommand(String command) throws DukeException {
    String[] words = command.trim().split("\\s");
    if (words[0].equals("bye") && words.length == 1) {
      return (taskList, storage) -> false;
    } else if (words[0].equals("list") && words.length == 1) {
      return (taskList, Storage) -> {
        taskList.listTasks();
        return true;
      };
    } else if (words[0].equals("delete") && words.length == 2) {
      return (taskList, storage) -> {
        taskList.deleteTask(words[1]);
        storage.saveFile(taskList);
        return true;
      };
    } else if (words[0].equals("mark") && words.length == 2) {
      return (taskList, storage) -> {
        taskList.markTask(words[1]);
        storage.saveFile(taskList);
        return true;
      };
    } else if (words[0].equals("unmark") && words.length == 2) {
      return (taskList, storage) -> {
        taskList.unmarkedTask(words[1]);
        storage.saveFile(taskList);
        return true;
      };
    } else if ((words[0].equals("deadline") || words[0].equals("todo") || words[0].equals("event"))) {
      return (taskList, storage) -> {
        taskList.addTask(this.createTask(words));
        storage.saveFile(taskList);
        return true;
      };
    } else if (words[0].equals("find")) {
      String expr = command.trim().substring(4).trim();
      return (taskList, storage) -> {
        taskList.findTasks(expr);
        return true;
      };
    } else {
      throw new InvalidCommandException();
    }
  }
}
