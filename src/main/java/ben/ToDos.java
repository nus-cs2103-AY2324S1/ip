package ben;
public class ToDos extends Task {

    public ToDos(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String saveString() {
        return "T|" + super.saveString();
    }
}
