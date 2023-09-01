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
            String time = next.substring(5, len - 1);
            t = new Deadline(desc, time);
        } else {
            int endDesc = next.indexOf("(from: ");
            String desc = next.substring(4, endDesc);
            int endFrom = next.indexOf("to: ");
            String from = next.substring(endDesc - 6, endFrom);
            String to = next.substring(endFrom - 3, next.length() - 2);
            t = new Event(desc, from, to);
        }

        if (next.startsWith("[X]")) {
            t.mark();
        } else {
            t.unmark();
        }

        return t;
    }

    public String toString(){
        return "[" + getStatusIcon() + "]" + this.description;
    }
}
