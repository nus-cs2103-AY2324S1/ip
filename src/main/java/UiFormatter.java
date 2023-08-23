public class UiFormatter {


  public String displayTask(Task task) {
    String answer = "";

    if (task instanceof Todo) {
      answer = "[" + Todo.taskType + "]" + "[" + task.getDoneIcon() + "] " + task.description;
    } else if (task instanceof Deadline) {
      Deadline deadTask = (Deadline) task;

      answer =
          "[" + Deadline.taskType + "]" + "[" + task.getDoneIcon() + "] " + task.description + "("
              + deadTask.getDeadDate() + ")";

    } else if (task instanceof Event) {
      Event eventTask = (Event) task;

      answer =
          "[" + Event.taskType + "]" + "[" + task.getDoneIcon() + "] " + task.description + "("
              + eventTask.getStartDate() + " to " + eventTask.getEndDate() + ")";
    }

    return answer;
  }

}
