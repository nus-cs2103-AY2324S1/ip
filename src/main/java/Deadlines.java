public class Deadlines extends TaskWithDeadline {

    public Deadlines(String description, String by) {
        super(description, by);
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getEnd());
    }

    @Override
    public String toSave() {
        return String.format("D|%s|%s|%s", this.getStatusIcon(), this.getDescription(), this.getEndSave());
    }
}
