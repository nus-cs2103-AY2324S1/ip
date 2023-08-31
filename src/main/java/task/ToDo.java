package task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[T]");
        sb.append("[" + getStatusIcon() + "] ");
        sb.append(description);
        return sb.toString();
    }
}
