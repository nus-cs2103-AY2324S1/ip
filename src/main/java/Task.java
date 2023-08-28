public class Task {
    protected String task;
    protected boolean toBeDone;

    public Task(String task) {
        this.task = task;
        this.toBeDone = true;
    }

    public void mark() {
        toBeDone = false;
    }

    public void unmark() {
        toBeDone = true;
    }

    public String getStatusIcon() {
        return (toBeDone ? "[ ]" : "[X]");
    }

    public String toFileString() {
        if (this instanceof Todo) {
            Todo todo = (Todo) this;
            return todo.toString();
        } else if (this instanceof Event) {
            Event event = (Event) this;
            return event.toString();
        } else if (this instanceof Deadline) {
            Deadline deadline = (Deadline) this;
            return deadline.toString();
        } else {
            return this.toString();
        }
    }

    public static Task fromFileString(String fileString) {
        String target =  fileString;
        String[] split = target.split("]\\[");
        String splitOne = split[0]; // [D
        String[] toGetTaskType = splitOne.split("\\[");
        String taskType = toGetTaskType[1]; // D

        String splitTwo = split[1]; // " ] soc (by:sun)"
        String[] toGetStatusIcon = splitTwo.split("] ");
        String statusIcon = toGetStatusIcon[0]; // " "
        boolean isDone = false;

        if (statusIcon.equals(" ")) {
            isDone = false;
        } else {
            isDone = true;
        }

        String taskDescription = toGetStatusIcon[1]; // soc (by: sun)

        Task newTask;

        if (taskType.equals("T")) {
            newTask = new Todo(taskDescription);
        } else if (taskType.equals("E")) {
            String[] toGetFromTo = taskDescription.split(" \\(from: | to: |\\)"); // soc (from: mon to: tues)
            String task = toGetFromTo[0]; // soc
            String from = toGetFromTo[1]; // mon
            String to = toGetFromTo[2]; // tues
            newTask = new Event(task, from, to);

        } else if (taskType.equals("D")) {
            String[] toGetBy = taskDescription.split(" \\(by: |\\)"); // soc (by: sun)
            String task = toGetBy[0]; // soc
            String by = toGetBy[1]; // sun
            newTask = new Deadline(task, by);

        } else {
            newTask = new Task(taskDescription);
        }

        if (isDone) {
            newTask.mark();
        }

        return newTask;
    }



    @Override
    public String toString() {
        return getStatusIcon() + " " + task;
    }
}
