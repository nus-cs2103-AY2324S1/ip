package tasks;

import java.time.LocalDateTime;

public class Deadline extends TaskAbstract {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String saveToTextFormat() {
        return String.format("D | %s | %s | %s", this.isDone ? "1" : "0", this.description, this.deadline);
    }

    @Override
    public void printStatus() {
        System.out.printf("[D][%s] %s (by: %s)\n", this.isDone ? "X" : " ", this.description, this.deadline);
    }
}
