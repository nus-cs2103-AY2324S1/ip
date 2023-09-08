package duke.assets.tasks;

public class Todo extends TaskAbstract {
    public Todo(String description) {
        super(description);
    }

    public String saveToTextFormat() {
        return String.format("T | %s | %s", this.isDone ? "1" : "0", this.description);
    }

    @Override
    public void printStatus() {
        System.out.printf("[T][%s] %s\n", this.isDone ? "X" : " ", this.description);
    }
}
