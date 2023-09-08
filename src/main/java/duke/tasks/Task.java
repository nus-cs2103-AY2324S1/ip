package duke.tasks;

public abstract class Task {
    private boolean done;
    private final String name;

    public abstract String dataString();

    private Task() {
        this.name = "";
        done = false;
    }

    public Task(String name) {
        this.name = name;
        done = false;
    }

    public void setdone() {
        done = true;
    }

    public boolean isdone() {
        return this.done;
    }

    public String getname() {
        return this.name;
    }

    public boolean containsStr(String str) {
        return name.toLowerCase().contains(str.toLowerCase());
    }
    @Override
    public String toString() {
        String box;

        if (done) {
            box = "[X] ";
        } else {
            box = "[ ] ";
        }

        return box + name;
    }
}
