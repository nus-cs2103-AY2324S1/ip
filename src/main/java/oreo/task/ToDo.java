package oreo.task;

public class ToDo extends Task {
    public ToDo(String d) {
        super(d);
    }

    public ToDo(String d, boolean completed) {
        super(d);
        this.completed = completed;
    }

    @Override
    public String toString() {
        String marker = "[ ]";
        if (this.completed) marker = "[X]";
        return  "[T]" + marker + " " + this.description + "\n";
    }

    @Override
    public String writeToFile() {
        int mark = completed ? 1 : 0;
        String data = 1 + " " + mark + description + System.lineSeparator();
        return data;
    }
}
