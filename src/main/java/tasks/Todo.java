package tasks;

public class Todo extends TaskAbstract {
    public Todo(String description) {
        super(description);
    }

    @Override
    public void printStatus() {
        System.out.printf("[T][%s] %s\n", this.isDone ? "X" : " ", this.description);
    }
}
