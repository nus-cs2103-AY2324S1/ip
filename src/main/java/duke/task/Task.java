package duke.task;

//The duke.task.Task class is in charge of creating task objects
//which can be of different types: ToDos, Deadlines and Events
public abstract class Task{
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }
    public String getTask() {
        return this.description;
    }

    public String taskString() {
        String str = this.isDone ? "[X] " : "[ ] ";
        String output = str + this.description;
        return output;
    }

    public abstract String saveTaskString();
}