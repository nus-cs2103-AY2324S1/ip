public class TodoTask extends Task {
    public TodoTask(String name) {
        super(name);
    }
    @Override
    public String toString() {
        String typeIcon = "[T]";
        return String.format("%s%s", typeIcon, super.toString());
    }
}
