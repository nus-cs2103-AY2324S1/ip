package io;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Ui {

  /**
   * Displays the greeting message to the user
   */
  public void displayGreetings() {
    String greeting = "Hello! I'm KimochiUsagi (きもち　うさぎ)!\n";
    String info = "Ask the bunny a question!\n";
    System.out.println(greeting);
    System.out.println(info);
  }


  /**
   * Displays a message and the display of a given Task one after the other.
   *
   * @param message The message to be displayed.
   * @param task    The Task object whose display will be shown.
   */
  public void displayAction(String message, Task task) {
    System.out.println(message);
    System.out.println(this.displayTask(task));

  }


  /**
   * Displays the goodbye text.
   */
  public void displayGoodbye() {
    String goodbye = "Bye. See you again! (またね)";
    System.out.println(goodbye);

  }

  /**
   * Generates and returns the appropriate display string for the given Task object, based on its
   * subtype.
   *
   * @param task The Task object for which to generate the display.
   * @return The display string representing the given task.
   */
  public String displayTask(Task task) {
    String answer = "";

    if (task instanceof Todo) {
      answer = "[" + Todo.taskType + "]" + "[" + task.getDoneIcon() + "] " + task.getDescription();
    } else if (task instanceof Deadline) {
      Deadline deadTask = (Deadline) task;

      answer =
          "[" + Deadline.taskType + "]" + "[" + task.getDoneIcon() + "] " + task.getDescription()
              + "("
              + deadTask.getDeadDate() + ")";

    } else if (task instanceof Event) {
      Event eventTask = (Event) task;

      answer =
          "[" + Event.taskType + "]" + "[" + task.getDoneIcon() + "] " + task.getDescription() + "("
              + eventTask.getStartDate() + " to " + eventTask.getEndDate() + ")";
    }

    return answer;
  }

}
