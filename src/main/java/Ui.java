public class Ui {


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
