public class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) throws AlexException {
        description = description.stripTrailing();
        if (description.equals("")) {
            String message = "OOPS!!! The description of a task cannot be empty";
            throw new AlexException(message);
        }
        this.description = description;
    }

    public String getStatusSymbol() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
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

    public void mark(boolean printToUser) {
        if (printToUser) {
            this.mark();
        } else {
            String tobePrinted = "";
            this.isDone = true;
        }
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
