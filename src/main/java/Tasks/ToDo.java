package Tasks;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public void printStatus() {
        System.out.printf("[T][%s] %s\n", this.isDone ? "X" : " ", this.description);
    }
}
