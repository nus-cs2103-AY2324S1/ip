public class ToDos extends Task {
    public ToDos(String task, int taskId) {
        super(task, taskId);
    }

    @Override
    public String getTask() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        return taskId + "." + "[T]" + checkbox + task;
    }
    @Override
    public String checkBox() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        return "[T]" + checkbox + task;
    }
}
