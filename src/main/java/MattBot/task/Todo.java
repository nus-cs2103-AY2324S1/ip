public class Todo extends Task {
    Todo(String name) {
        super(name, false);
    }
    Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    public String identifier() {
        return "T";
    }

    /*
     * Returns String form for storage.
     *
     * @returns String for storage format.
     */
    public String toFile() {
        return identifier() + " | " + showStatusAsFile() + " | " + showName();
    }
}
