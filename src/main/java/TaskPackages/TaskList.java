package TaskPackages;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import Utility.NoSuchEntryException;
import Utility.DukeException;
import Utility.IncorrectFormatException;
import Utility.InvalidNumberException;

public class TaskList {
  protected ArrayList<Task> list;

  protected static class DeadlineWrapper {
    String description;
    LocalDate byDate;
    LocalTime byTime;

    protected DeadlineWrapper(String description, LocalDate byDate, LocalTime byTime) {
      this.description = description;
      this.byDate = byDate;
      this.byTime = byTime;
    }
  }

  protected static class EventWrapper {

    protected String description;
    protected LocalDate fromDate;
    protected LocalTime fromTime;
    protected LocalDate toDate;
    protected LocalTime toTime;

    protected EventWrapper(String description, LocalDate fromDate, LocalTime fromTime, LocalDate toDate, LocalTime toTime) {
      this.description = description;
      this.fromDate = fromDate;
      this.fromTime = fromTime;
      this.toDate = toDate;
      this.toTime = toTime;
    }
  }

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

  public static DeadlineWrapper deadlineParser(String input) throws IncorrectFormatException{
    int index = input.indexOf(" /by ");
    if (index > -1) {
      try {
        String description;
        LocalDate byDate;
        LocalTime byTime;
        String[] tempString = input.split(" /by ", 2);
        String[] tempDate = tempString[1].split(" ", 2);

        description = tempString[0];

        if (LocalDate.parse(tempDate[0]) instanceof LocalDate) {
          byDate = LocalDate.parse(tempDate[0]);
        } else if (LocalDate.parse(tempDate[1]) instanceof LocalDate) {
          byDate = LocalDate.parse(tempDate[1]);
        } else {
          byDate = null;
        }

        if (LocalTime.parse(tempDate[0]) instanceof LocalTime) {
          byTime = LocalTime.parse(tempDate[0]);
        } else if (LocalTime.parse(tempDate[1]) instanceof LocalTime) {
          byTime = LocalTime.parse(tempDate[1]);
        } else {
          byTime = null;
        }

        return new DeadlineWrapper(description, byDate, byTime);
        
      } catch (Exception e) {
        throw new IncorrectFormatException();
      }
    } else {
        throw new IncorrectFormatException();
    }
  }

  public String addDeadline(String entry) throws DukeException{
    try{
      DeadlineWrapper parsedEntry = deadlineParser(entry);
      Deadline tempDeadline = new Deadline(parsedEntry.description, parsedEntry.byDate, parsedEntry.byTime);
      list.add(tempDeadline);
      return String.format("I've added this deadline:\n%s\nNow you have %d task(s) in the list.\n", tempDeadline.toString(), list.size());
    } catch (DukeException e) {
      throw e;
    }
  }

  public static EventWrapper eventParser(String input) throws IncorrectFormatException {
    int indexFrom = input.indexOf(" /from ");
    int indexTo = input.indexOf(" /to ");
    if (indexFrom > -1 && indexTo > -1) {
      String[] tempString = {input.substring(0, indexFrom), input.substring(indexFrom+7, indexTo), input.substring(indexTo+5, input.length())};
      try {
        String description = tempString[0];

      } catch (Exception e) {
        throw new IncorrectFormatException();
      }
    } else {
      throw new IncorrectFormatException();
    }
  }

  public String addEvent(String entry) throws DukeException{
    try {
      EventWrapper parsedEntry = eventParser(entry);
      Event tempEvent = new Event(parsedEntry.description, parsedEntry.fromDate, parsedEntry.fromTime, parsedEntry.toDate, parsedEntry.toTime);
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

  public int getSize() {
    return list.size();
  }
}
