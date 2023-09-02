public class ToDos extends Task{

    final String taskChar = "[T]";

    public ToDos(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return taskChar + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + super.getStatusNumber() + " | " + super.getTaskName();
    }

}
