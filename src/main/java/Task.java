public class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public String getStatusSymbol() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    public void mark() {
        String tobePrinted = "";
        this.isDone = true;
        tobePrinted = "_____________________________________________________________\n"
                    + "Nice! I've marked this task as done:\n"
                    + "   "
                    + this.toString()
                    + "\n"
                    + "_____________________________________________________________\n";
        System.out.println(tobePrinted);
    }

    public void unmark() {
        String tobePrinted = "";
        this.isDone = false;
        tobePrinted = "_____________________________________________________________\n"
                    + "OK, I've marked this task as not done yet:\n"
                    + "   "
                    + this.toString()
                    + "\n"
                    + "_____________________________________________________________\n";

        System.out.println(tobePrinted);
    }

    @Override
    public String toString() {
        String str = "[" + this.getStatusSymbol() + "] " + this.description;
        return str;
    }
}
