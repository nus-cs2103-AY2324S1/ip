package penguin;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public String getDisplay() {
        return "[T]" + super.getDisplay();
    }
}
