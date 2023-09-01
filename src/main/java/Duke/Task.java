package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    public String getStatusIcon() { //for tasks
        return (isDone ? "[X]" : "[ ]");
    }

    /*public void mark() {
        if (isDone == false) {
            isDone = true;
        } else {
            System.out.println("Haha nice going... This task is already done, bozo!");
        }
    } */
    public void mark() throws GmanException{
        try {
            if (isDone == true) {
                throw new GmanException("Haha nice going... This task is already done, bozo!");
            }
            isDone = true;
        } catch (GmanException e) {
            System.out.println(e.getMessage());
        }
    }

    public void unmark() throws GmanException{
        try {
            if (isDone == false) {
                throw new GmanException("Hey... this task was never done in the first place!");
            }
            isDone = false;
        } catch (GmanException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Task readFromFile(String line) throws GmanException {
        String[] segments = line.split(" \\| ");
        if (segments[0].equals("T")) {
            return Todo.readFromFile(segments);
        } else if (segments[0].equals("D")) {
            return Deadline.readFromFile(segments);
        } else if (segments[0].equals("E")) {
            return Event.readFromFile(segments);
        } else {
            return null;
        }
    }

    public String toWriteString() {
        String symbol = "O";
        if (this.isDone) {
            symbol = "X";
        }
        return (symbol + " | " + this.description);
    }



}
