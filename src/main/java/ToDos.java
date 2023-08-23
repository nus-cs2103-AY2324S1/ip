public class ToDos extends Task {
    public ToDos(String task, int taskId) {
        super(task, taskId);
    }

    @Override
    public void mark() {
        this.done = true;
        System.out.println(super.line() + "Okay, I have marked this task as completed!" + "\n" + this.toString());
        System.out.println(super.line());
    }
    @Override
    public void unMark() {
        this.done = false;
        System.out.println(super.line() + "Okay, I have marked this task as incomplete!" + "\n" + this.toString());
        System.out.println(super.line());
    }
    @Override
    public String getTask() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        return taskId + "." + "[T]" + checkbox + task;
    }
    @Override
    public String toString() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        return "[T]" + checkbox + task;
    }
}
