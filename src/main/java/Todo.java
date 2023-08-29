import java.time.LocalDate;

public class Todo extends Task {

    private static String TYPE = "[T]";

    public Todo(String task) {
        super(task);
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        return this.TYPE + super.toString();
    }
}
