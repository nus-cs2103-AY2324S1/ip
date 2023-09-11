package dude.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }
    @Override
    public String getType() {
        return "todo";
    }
    @Override
    public String saveTask() {
        String data = "T | ";
        if (this.isDone()) {
            data += "1 | ";
        } else {
            data += "0 | ";
        }
        data = data + this.getDescription() + "\n";
        return data;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
