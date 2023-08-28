package TaskPackages;
import java.util.ArrayList;
import Utility.NoSuchEntryException;
import Utility.DukeException;
import Utility.IncorrectFormatException;
import Utility.InvalidNumberException;

public class TaskList {
  protected ArrayList<Task> list;

  public TaskList() {
    this.list = new ArrayList<Task>();
  }

  public void addTask(String entry) {
    list.add(new Task(entry));
  }

  public String addTodo(String entry) throws IncorrectFormatException{
    if (entry.equals("")) {
      throw new IncorrectFormatException();
    } else {
      Todo tempTodo = new Todo(entry);
      list.add(tempTodo);
      return String.format("I've added this task:\n%s\nNow you have %d tasks in the list.\n", tempTodo.toString(), list.size());
    }
  }

  public static String[] deadlineParser(String input) throws IncorrectFormatException{
    int index = input.indexOf(" /by ");
    if (index > -1) {
        return input.split(" /by ", 2);
    } else {
        throw new IncorrectFormatException();
    }
  }

  public String addDeadline(String entry) throws DukeException{
    try{
      String[] parsedEntry = deadlineParser(entry);
      Deadline tempDeadline = new Deadline(parsedEntry[0], parsedEntry[1]);
      list.add(tempDeadline);
      return String.format("I've added this deadline:\n%s\nNow you have %d task(s) in the list.\n", tempDeadline.toString(), list.size());
    } catch (DukeException e) {
      throw e;
    }
  }

  public static String[] eventParser(String input) throws IncorrectFormatException {
    int indexFrom = input.indexOf(" /from ");
    int indexTo = input.indexOf(" /to ");
    if (indexFrom > -1 && indexTo > -1) {
      String[] tempString = {input.substring(0, indexFrom), input.substring(indexFrom+7, indexTo), input.substring(indexTo+5, input.length())};
      return tempString;
    } else {
      throw new IncorrectFormatException();
    }
  }

  public String addEvent(String entry) throws DukeException{
    try {
      String[] parsedEntry = eventParser(entry);
      Event tempEvent = new Event(parsedEntry[0], parsedEntry[1], parsedEntry[2]);
      list.add(tempEvent);
      return String.format("I've added this event:\n%s\nNow you have %d task(s) in the list.\n", tempEvent.toString(), list.size());
    } catch (DukeException e) {
      throw e;
    }
  }

  public String markAsDone(String input) throws DukeException{
    try {int index = Integer.parseInt(input) - 1;
      if (index < 0 || index + 1 > list.size()) {
          throw new NoSuchEntryException();
      } else {
        if (list.get(index).isDone) {
          return "This task is already marked as done!\n" + list.get(index).toString() + "\n";
        } else {
          list.get(index).setDoneness(true);
          return "This task is now marked as done!\n" + list.get(index).toString() + "\n";
        }
      }
    } catch (NumberFormatException e) {
      throw new InvalidNumberException();
    } 
  }

  public String unmarkAsDone(String input) throws DukeException {
    try {int index = Integer.parseInt(input) - 1;
      if (index < 0 || index + 1 > list.size()) {
          throw new NoSuchEntryException();
      } else {
        if (!list.get(index).isDone) {
          return "This task is already marked as not done!\n" + list.get(index).toString() + "\n";
        } else {
          list.get(index).setDoneness(false);
          return "This task is now marked as not done!\n" + list.get(index).toString() + "\n";
        }
      } 
    } catch (NumberFormatException e) {
      throw new InvalidNumberException();
    } 
  }

  public String delete(String input) throws DukeException {
    try {int index = Integer.parseInt(input) - 1;
      if (index < 0 || index + 1 > list.size()) {
          throw new NoSuchEntryException();
      } else {
        String tempString =  list.get(index).toString();
        list.remove(index);
        return String.format("I've removed this task:\n%s\nNow you have %d task(s) in the list.\n", tempString, list.size());
      }
    } catch (NumberFormatException e) {
      throw new InvalidNumberException();
    }
  }

  public String toString() {
    String returnString = new String("");
    int i = 1;
    for(Task entry : list) {
      returnString += (i + "." + entry.toString() + "\n");
      i++;
    }
    returnString += String.format("You have %d task(s) in the list.\n", i-1);
    return returnString;
  }

  public boolean isEmpty() {
    return list.size() == 0;
  }
  
  public String clearList() {
    String tempString = list.get(0).toFileString();
    list.remove(0);
    return tempString;
  }
}
