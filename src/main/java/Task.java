import java.time.LocalDateTime;

public abstract class Task {
        protected String description;
        protected boolean isDone;

        protected boolean isSplit;

        public Task(String description, boolean isDone) {
            this.description = description;
            this.isDone = isDone;
            this.isSplit = false;
        }

        public String getStatusIcon() {
            return (isDone ? "[X] " : "[ ] ");
        }

        public void markStatus() {
            this.isDone = true;
        }

        public void unmarkStatus() {
            this.isDone = false;
        }



        public abstract String saveToFileString();

        @Override
        public String toString() {
            return this.description;
        }


        public static Task loadTaskFromFile(String lineToLoad) {
            String[] taskComponents = lineToLoad.split(" \\| ");
            String taskType = taskComponents[0];
            boolean isDone = taskComponents[1].equals("1");
            String taskDescription = taskComponents[2];

            if (taskType.equals("T")) {
                return new ToDo(taskDescription, isDone);
            }
            if (taskType.equals("D")) {
/*                    String deadline = taskComponents[3];*/
                LocalDateTime deadlineDateTime = DateConverter.convertToDateTime(taskComponents[3]);
                Deadline newDeadlineTask = new Deadline(taskDescription, deadlineDateTime, isDone);
                return newDeadlineTask;
            }
            if (taskType.equals("E")) {
/*                    String eventFrom = taskComponents[3];
                String eventTo = taskComponents[4];*/
                LocalDateTime eventFrom = DateConverter.convertToDateTime(taskComponents[3]);
                LocalDateTime eventTo = DateConverter.convertToDateTime(taskComponents[4]);
                Event newEventTask = new Event(taskDescription, eventFrom, eventTo, isDone);
                return newEventTask;
            }
            return null;
        }
}
