public class Deadline extends Task {
    protected String date;
    Deadline(String name, String date) {
        super(name, false);
        this.date = date;
    }
    Deadline(String name, boolean isDone, String date ) {
        super(name, isDone);
        this.date = date;
    }
    public String getDate() {
        return this.date;
    }
    public String identifier() {
        return "D";
    }

    /*
     * Returns String form for storage.
     *
     * @returns String for storage format.
     */
    public String toFile() {
        return identifier() + " | " + showStatusAsFile() + " | " + showName() + " | " + getDate();
    }
    @Override
    public String toString() {
        return String.format("[%s] [%s] %s (by: %s)", this.identifier(), this.showStatus(), this.showName(), this.getDate());
    }
}
