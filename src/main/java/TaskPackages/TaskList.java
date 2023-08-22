package TaskPackages;
import java.util.ArrayList;

public class TaskList {
  protected ArrayList<Task> list;

  public TaskList() {
    this.list = new ArrayList<Task>();
  }

  public void addList(String entry) {
      list.add(new Task(entry));
  }

  public String markAsDone(String command) {
    String[] splitComment = command.split(" ", 2);
    try {int index = Integer.parseInt(splitComment[1]) - 1;
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

  public String unmarkAsDone(String command) {
    String[] splitComment = command.split(" ", 2);
    try {int index = Integer.parseInt(splitComment[1]) - 1;
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
    returnString += "Now you have " + (i-1) + " tasks in the list.\n";
    return returnString;
  }
}
