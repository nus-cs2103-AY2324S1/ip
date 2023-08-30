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

/*        public void splitDescription() {
            if (!isSplit) {
                String[] splitDesc = description.split("\\s+", 2);
                this.description = splitDesc[1];
                isSplit = true;
            }
        }*/

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
            } else if (taskType.equals("D")) {
                String deadline = taskComponents[3];
                Deadline newDeadlineTask = new Deadline(taskDescription, deadline, isDone);
                return newDeadlineTask;
            } else if (taskType.equals("E")) {
                String eventFrom = taskComponents[3];
                String eventTo = taskComponents[4];
                Event newEventTask = new Event(taskDescription, eventFrom, eventTo, isDone);
                return newEventTask;
            } else {
                return null;
            }
        }
}
