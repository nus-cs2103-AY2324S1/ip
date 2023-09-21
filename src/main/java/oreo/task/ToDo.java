package oreo.task;

public class ToDo extends Task {
    public ToDo(String d) {
        super(d);
    }

    public ToDo(String d, boolean completed) {
        super(d);
        this.isComplete = completed;
    }

    @Override
    public String toString() {
        String marker = "☐";
        if (this.isComplete) {
            marker = "☑";
        }
        return  "\uD83C\uDD83:" + " " + this.description + " " + marker + "\n";
    }

    @Override
    public String writeToFile() {
        int mark = isComplete ? 1 : 0;
        String data = 1 + " " + mark + description + System.lineSeparator();
        return data;
    }

    @Override
    public String getTaskInEditFormat() {
        return "todo" + description;
    }
}
