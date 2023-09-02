package Alex;

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
        this.isDone = true;
        String tobePrinted = Ui.horizontalLine
                    + "Nice! I've marked this task as done:\n"
                    + "   "
                    + this
                    + "\n"
                    + Ui.horizontalLine;
        System.out.println(tobePrinted);
    }

    public void mark(boolean printToUser) {
        if (printToUser) {
            this.mark();
        } else {
            this.isDone = true;
        }
    }

    public void unmark() {
        this.isDone = false;
        String tobePrinted = Ui.horizontalLine
                    + "OK, I've marked this task as not done yet:\n"
                    + "   "
                    + this
                    + "\n"
                    + Ui.horizontalLine;

        System.out.println(tobePrinted);
    }

    @Override
    public String toString() {
        String str = "[" + this.getStatusSymbol() + "] " + this.description;
        return str;
    }
}
