package Jeoe.Tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String replyString (int currNumOfTask) {
        return super.replyString(currNumOfTask);
    }
}
