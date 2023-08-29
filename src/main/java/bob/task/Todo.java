package bob.task;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean done) {
        super(name);
        super.done = done;
    }

    /**
     * Converts object to string representation for user display
     * @return string representation
     */
    @Override
    public String toString() {
        String done = this.done ? "[X]" : "[ ]";
        return "[T]" + done + " " + this.name;
    }

    /**
     * Parses string into a Todo object
     * @param str is in the form e.g. "0 | read book"
     * @return Todo object
     * @throws IndexOutOfBoundsException when parsing fails, as string split does not occur correctly.
     */
    public static Todo parseTodo(String str) throws IndexOutOfBoundsException {
        String[] strSplit = str.split(" \\| ", 2);

        boolean isDone = strSplit[0].equals("1");
        String name = strSplit[1];

        return new Todo(name, isDone);
    }

    /**
     * Converts object into string to be stored in bob.txt
     * @return string representation
     */
    @Override
    public String toTxt() {
        String separation = " | ";
        return "todo" + separation + (done ? 1 : 0) + separation + name;
    }
}
