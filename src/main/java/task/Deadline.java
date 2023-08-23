package task;

public class Deadline extends Task {
    private String date;
    public Deadline(String name, String date) {
        this.name = name;
        this.date = date;
        this.status = false;
    }

    @Override
    public String toString() {
        String statusMark = this.status ? "[✓]" : "[✕]";
        return String.format("[D]%s %s (by: %s)", statusMark, name, date);
    }
}
