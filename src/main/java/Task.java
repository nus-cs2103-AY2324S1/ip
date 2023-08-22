public class Task {

    String list;

    boolean completed;

    public Task(String list) {
        this.list = list;
    }

    public String setMarked() {
        this.completed = true;
        return "";
    }

    public String setUnmarked() {
        this.completed = false;
        return "";
    }

    @Override
    public String toString() {
        return (this.completed ? "[X] " : "[ ] ") + this.list;
    }
}
