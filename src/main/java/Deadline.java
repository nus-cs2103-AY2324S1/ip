public class Deadline extends Task {
    String due;

    public Deadline(String d, String due) {
        super(d);
        this.due = due;
    }

    public Deadline(String d, String due, boolean completed) {
        super(d);
        this.due = due;
        this.completed = completed;
    }

    @Override
    public String toString() {
        String marker = "[ ]";
        if (this.completed) marker = "[X]";
        return  "[D]" + marker + " " +
                this.description +
                "(by:" + this.due + ")\n";
    }

    @Override
    public String writeToFile() {
        int mark = completed ? 1 : 0;
        String data = 2 + " " + mark + description + "/" + due +
                System.lineSeparator();
        return data;
    }
}
