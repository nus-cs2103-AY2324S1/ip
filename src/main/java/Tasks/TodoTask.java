package Tasks;

public class TodoTask extends Task {

    public TodoTask(String itemName) {
        super(itemName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
