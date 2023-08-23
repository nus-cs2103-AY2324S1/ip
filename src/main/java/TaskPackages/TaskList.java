package TaskPackages;
import java.util.ArrayList;

public class TaskList {
  protected ArrayList<Task> list;

  public TaskList() {
    this.list = new ArrayList<Task>();
  }

  public void addTask(String entry) {
    list.add(new Task(entry));
  }

  public String addTodo(String entry) {
    Todo tempTodo = new Todo(entry);
    list.add(tempTodo);
    return String.format("I've added this task:\n%s\nNow you have %d tasks in the list.\n", tempTodo.toString(), list.size());
  }

  public static String[] deadlineParser(String input) {
    int index = input.indexOf(" /by ");
    if (index > -1) {
        return input.split(" /by ", 2);
    } else {
        String[] tempString = {input, ""};
        return tempString;
    }
  }

  public String addDeadline(String entry) {
    String[] parsedEntry = deadlineParser(entry);
    Deadline tempDeadline = new Deadline(parsedEntry[0], parsedEntry[1]);
    list.add(tempDeadline);
    return String.format("I've added this deadline:\n%s\nNow you have %d tasks in the list.\n", tempDeadline.toString(), list.size());
  }

  public static String[] eventParser(String input) {
    int indexFrom = input.indexOf(" /from ");
    int indexTo = input.indexOf(" /to ");
    if (indexFrom > -1 && indexTo > -1) {
      String[] tempString = {input.substring(0, indexFrom), input.substring(indexFrom+7, indexTo), input.substring(indexTo+5, input.length())};
      return tempString;
    } else {
      String[] tempString = {input, "", ""};
      return tempString;
    }
  }

  public String addEvent(String entry) {
    String[] parsedEntry = eventParser(entry);
    Event tempEvent = new Event(parsedEntry[0], parsedEntry[1], parsedEntry[2]);
    list.add(tempEvent);
    return String.format("I've added this event:\n%s\nNow you have %d tasks in the list.\n", tempEvent.toString(), list.size());
  }

  public String markAsDone(String input) {
    try {int index = Integer.parseInt(input) - 1;
      if (index < 0 || index + 1 > list.size()) {
          return "This task does not exist!\n";
      } else {
        if (list.get(index).isDone) {
          return "This task is already marked as done!\n" + list.get(index).toString() + "\n";
        } else {
          list.get(index).setDoneness(true);
          return "This task is now marked as done!\n" + list.get(index).toString() + "\n";
        }
      }
    } catch (NumberFormatException e) {
      return "You didn't specify the task number!\n";
    } 
  }

  public String unmarkAsDone(String input) {
    try {int index = Integer.parseInt(input) - 1;
      if (index < 0 || index + 1 > list.size()) {
          return "This task does not exist!\n";
      } else {
        if (!list.get(index).isDone) {
          return "This task is already marked as not done!\n" + list.get(index).toString() + "\n";
        } else {
          list.get(index).setDoneness(false);
          return "This task is now marked as not done!\n" + list.get(index).toString() + "\n";
        }
      } 
    } catch (NumberFormatException e) {
      return "You didn't specify the task number!\n";
    } 
  }

  public String toString() {
    String returnString = new String("");
    int i = 1;
    for(Task entry : list) {
      returnString += (i + "." + entry.toString() + "\n");
      i++;
    }
    returnString += String.format("You have %d tasks in the list.\n", i-1);
    return returnString;
  }
}
