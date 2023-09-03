package duke;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a list of tasks, and supports methods like add, delete, mark, unmarked.
 */
public class TaskList {

  private LinkedList<Task> tasks = new LinkedList<>();

  /**
   * Creates a new TaskList with the given list.
   * @param tasks the tasks to be created
   */
  public TaskList(LinkedList<Task> tasks) {
    this.tasks = tasks;
  }

  public LinkedList<Task> getTaskList() {
    return this.tasks;
  }


  /**
   * List the current state of the tasks list
   */
  public void listTasks(Pane pane, Image dukeImage) {
    int i = 1;
    StringBuilder displayMsg = new StringBuilder("Here are the tasks in your list:\n");
    for (Task task : tasks) {
      displayMsg.append("    ").append(i).append(". ").append(task).append("\n");
      i += 1;
    }
    pane.getChildren().add(DialogBox.getDukeDialog(displayMsg.toString(), dukeImage));
  }

  /**
   * Adds the task to the list
   *
   * @param task the task to be added
   */
  public void addTask(Task task, Pane pane, Image dukeImage) {
    tasks.add(task);
    String displayMsg = String.format("Got it. I've added this task:\n"+
            "    %s\n" +
            "Now you have %s tasks in the list.", task.toString(), tasks.size());
    pane.getChildren().add(DialogBox.getDukeDialog(displayMsg, dukeImage));
  }

  /**
   * Deletes the task at the specified position
   *
   * @param index position of task to be deleted
   * @throws WrongIndexException when index is out of bounds, or too big to parse
   */
  public void deleteTask(String index, Pane pane, Image dukeImage) throws WrongIndexException {
    try {
      String regex = "\\d+";
      if (!index.matches(regex) || Integer.parseInt(index, 10) - 1 < 0
              || Integer.parseInt(index, 10) - 1 >= tasks.size()) {
        throw new WrongIndexException();
      }
      int i = Integer.parseInt(index, 10) - 1;

      Task task = tasks.remove(i);
      String displayMsg = String.format("Noted. I've removed this task:\n " +
              "    %s\n" +
              "Now you have %s tasks in the list.", task.toString(), tasks.size());
      pane.getChildren().add(DialogBox.getDukeDialog(displayMsg, dukeImage));
    } catch (NumberFormatException e) {
      throw new WrongIndexException();
    }
  }

  /**
   * Marks the task as completed at the specified position
   *
   * @param index the position of the tasks to be mark completed
   * @throws WrongIndexException when index is out of bounds, or too big to parse
   */
  public void markTask(String index, Pane pane, Image dukeImage) throws WrongIndexException {
    try {
      String regex = "\\d+";
      if (!index.matches(regex) || Integer.parseInt(index, 10) - 1 < 0
              || Integer.parseInt(index, 10) - 1 >= tasks.size()) {
        throw new WrongIndexException();
      }
      int i = Integer.parseInt(index, 10) - 1;
      Task task = tasks.get(i);
      task.markCompleted();
      String displayMsg = String.format("Nice! I've marked this task as done:\n " +
              "    %s", task.toString());
      pane.getChildren().add(DialogBox.getDukeDialog(displayMsg, dukeImage));
    } catch (NumberFormatException e) {
      throw new WrongIndexException();
    }
  }

  /**
   * Unmarked the task as completed at the specified position.
   *
   * @param index the position of the tasks to be unmarked
   * @throws WrongIndexException when index is out of bounds, or too big to parse
   */
  public void unmarkedTask(String index, Pane pane, Image dukeImage) throws WrongIndexException {
    try {
      String regex = "\\d+";
      if (!index.matches(regex) || Integer.parseInt(index, 10) - 1 < 0
              || Integer.parseInt(index, 10) - 1 >= tasks.size()) {
        throw new WrongIndexException();
      }

      int i = Integer.parseInt(index, 10) - 1;
      Task task = tasks.get(i);
      task.markNotCompleted();
      String displayMsg = String.format("OK, I've marked this task as not done yet:\n " +
              "    %s", task.toString());
      pane.getChildren().add(DialogBox.getDukeDialog(displayMsg, dukeImage));
    } catch (NumberFormatException e) {
      throw new WrongIndexException();
    }
  }

  /**
   * Finds all the tasking matching the expression
   *
   * @param expr the expression to match
   * @return true if there is a match, false otherwise
   */
  public boolean findTasks(String expr, Pane pane, Image dukeImage) {
    StringBuilder displayMsg = new StringBuilder("Here are the matching tasks in your list:\n");

    if (expr.equals("")) {
      return false;
    }
    boolean match = false;
    int i = 1;
    Pattern p = Pattern.compile(expr);
    for (Task task : tasks) {
      Matcher m = p.matcher(task.toString());
      if (m.find()) {
        displayMsg.append("    ").append(i).append(". ").append(task).append("\n");
        match = true;
      }
      i += 1;
    }
    pane.getChildren().add(DialogBox.getDukeDialog(displayMsg.toString(), dukeImage));
    return match;
  }
}
