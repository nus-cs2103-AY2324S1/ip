public class ToDo extends Task {
    public ToDo(String d) {
        super(d);
    }

    @Override
    public String toString() {
        String marker = "[ ]";
        if (this.completed) marker = "[X]";
        return  "[T]" + marker + " " + this.description + "\n";
    }
}
