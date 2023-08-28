package task;

public class Deadline extends Task {
    private String date;
    public Deadline(String name, String date) {
        this.name = name;
        this.date = date;
        this.status = false;
    }

    public Deadline(String name, boolean status, String date) {
        this.name = name;
        this.date = date;
        this.status = status;
    }

    @Override
    public String toString() {
        String statusMark = this.status ? "[✓]" : "[✕]";
        return String.format("[D]%s %s (by: %s)", statusMark, name, date);
    }

    @Override
    public String toSave() {
        return String.format("D%s%s%s%d%s%s", DISCRIMINATOR, name, DISCRIMINATOR, Boolean.compare(status, false), DISCRIMINATOR, date);
    }
}
