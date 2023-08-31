package TaskPackages;

import java.util.ArrayList;

import Exceptions.DukeException;
import Exceptions.InvalidNumberException;
import Exceptions.NoSuchEntryException;

import java.time.LocalDate;
import java.time.LocalTime;

public class TaskList {
  protected ArrayList<Task> list;

  public TaskList() {
    this.list = new ArrayList<Task>();
  }

  public String add(String description, boolean doneness) {
    Todo todo = new Todo(description, doneness);
    list.add(todo);
    return todo.toString();
  }

  public String add(String description, boolean doneness, LocalDate byDate, LocalTime byTime) {
    Deadline deadline = new Deadline(description, doneness, byDate, byTime);
    list.add(deadline);
    return deadline.toString();
  }

  public String add(String description, boolean doneness, LocalDate fromDate, LocalTime fromTime, LocalDate toDate, LocalTime toTime) {
    Event event = new Event(description, doneness, fromDate, fromTime, toDate, toTime);
    list.add(event);
    return event.toString();
  }

  public String setDoneness(boolean doneness, int index) throws DukeException {
    try {
      if (index < 0 || index + 1 > list.size()) {
        throw new NoSuchEntryException();
      } else {
        if (doneness) {
          if (list.get(index).doneness) {
            return "This task is already marked as done!\n" + list.get(index).toString();
          } else {
            list.get(index).setDoneness(true);
            return "This task is now marked as done!\n" + list.get(index).toString();
          }
        } else {
          if (!list.get(index).doneness) {
            return "This task is already marked as not done!\n" + list.get(index).toString();
          } else {
            list.get(index).setDoneness(false);
            return "This task is now marked as not done!\n" + list.get(index).toString();
          }
        }
      }
    } catch (NumberFormatException e) {
      throw new InvalidNumberException();
    }
  }

  public String delete(int index) throws DukeException {
    try {
      if (index < 0 || index + 1 > list.size()) {
        throw new NoSuchEntryException();
      } else {
        String tempString = list.get(index).toString();
        list.remove(index);
        return String.format("I've removed this task:\n%s\nNow you have %d task(s) in the list.", tempString,
            list.size());
      }
    } catch (NumberFormatException e) {
      throw new InvalidNumberException();
    }
  }

  public String toString() {
    String returnString = new String("");
    int i = 1;
    for (Task entry : list) {
      returnString += (i + "." + entry.toString() + "\n");
      i++;
    }
    returnString += String.format("You have %d task(s) in the list.", i - 1);
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
