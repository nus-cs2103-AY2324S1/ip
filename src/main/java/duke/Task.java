package duke;

public class Task {
        private String taskName;
        private boolean isDone;

        public Task(String taskName) {
            this.taskName = taskName;
            this.isDone = false;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public String getTaskName() {
            return this.taskName;
        }

        public boolean getIsDone() {
            return this.isDone;
        }

        public void unmarkAsDone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return "[" + (this.isDone ? "X" : " ") + "] " + this.taskName;
        }

        public String saveString() {
            return "";
        }

        public static Task parseTask(String data) {
          // T | 1 | read book
          // D | 0 | return book | June 6th
          // E | 0 | project meeting | Aug 6th | Aug 8th
          String[] dataArr = data.split(" \\| ");
          String taskType = dataArr[0];
          boolean isDone = dataArr[1].equals("1");
          // each task has at least 3 elements
          String taskName = dataArr[2];
          Task newTask;
          // todo
          if (taskType.equals("T")) {
              newTask = new ToDo(taskName);
          // deadline
          } else if (taskType.equals("D")) {
              newTask = new Deadline(taskName, dataArr[3]);
          // event
          } else {
              newTask = new Event(taskName, dataArr[3], dataArr[4]);
          }
          if (isDone) {
              newTask.markAsDone();
          }
          return newTask;
      }
}