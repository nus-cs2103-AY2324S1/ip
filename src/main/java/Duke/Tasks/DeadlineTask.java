package Duke.Tasks;

import Duke.Parser;

public class DeadlineTask extends Task {

    String taskDescription;
    String deadline;

    public DeadlineTask(String task) {
        super(task);
        String[] inputStringComponents = task.split("/");
        taskDescription = inputStringComponents[0];
        deadline = inputStringComponents[1].split(" ", 2)[1];
    }

    @Override
    public String toString() {
        return String.format("[D] | %s | %s | %s",
                this.isDone() ? "[X]" : "[ ]",
                taskDescription,
                Parser.convertTimeToString(deadline));
    }

    @Override
    public boolean equals(Object o) {
        if ((!(o instanceof DeadlineTask))) {
            return false;
        }
        DeadlineTask obj = (DeadlineTask) o;
        if (obj.taskDescription.equals(this.taskDescription)
                && obj.deadline.equals(this.deadline)) {
            return true;
        }
        return false;
    }
}

