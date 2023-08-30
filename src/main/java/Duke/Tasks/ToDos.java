package Duke.Tasks;

public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
        super.taskType = 'T';
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
