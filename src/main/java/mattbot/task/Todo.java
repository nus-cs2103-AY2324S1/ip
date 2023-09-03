package mattbot.task;
public class Todo extends Task {
    public Todo(String name) {
        super(name, false);
    }
    public Todo(String name, boolean isDone) {
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
