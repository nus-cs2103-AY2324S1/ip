public class ToDo extends Task{

    public ToDo(String taskName) {
        super(taskName,TaskType.TODO);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    // mark task as done and print out the line
    @Override
    public void markDone() {
        isDone = true;
    }

    // mark task as undone and print out the line
    @Override
    public void unmarkDone() {
        isDone = false;
    }
}
