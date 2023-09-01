import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    private void mark(){
        isDone = true;
    }

    private void unmark(){
        isDone = false;
    }

    public String description(int m) {
        String s;
        if (m == 0) {
            // mark
            mark();
            s = this.toString();
        } else {
            unmark();
            s = this.toString();
        }
        return s;
    }

    public static Task readFile(String nextLine) {
        String next = nextLine.substring(3);
        Task t;
        if(nextLine.startsWith("[T]")) {
            String desc = next.substring(4);
            t = new Todo(desc);
        } else if (nextLine.startsWith("[D]")) {
            int endDesc = next.indexOf("(by: ");
            String desc = next.substring(4, endDesc);
            int len = next.length();
            String time = next.substring(endDesc + 5, len - 1);
            LocalDate d1 = LocalDate.parse(time);
            t = new Deadline(desc, d1);
        } else {
            int endDesc = next.indexOf("(from: ");
            String desc = next.substring(4, endDesc);
            int endFrom = next.indexOf("to: ");
            String from = next.substring(endDesc + 7, endFrom - 1);
            String to = next.substring(endFrom + 4, next.length() - 1);
            LocalDate d1 = LocalDate.parse(from);
            LocalDate d2 = LocalDate.parse(to);
            t = new Event(desc, d1, d2);
        }

        if (next.startsWith("[X]")) {
            t.mark();
        } else {
            t.unmark();
        }

        return t;
    }

    public String toWrite() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String toString(){
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
