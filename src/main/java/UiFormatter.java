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

    }

    return answer;
  }

}
