public class TodoTask extends Task {

    private TodoTask(boolean isDone, String description) {
        super(isDone, description);
    }

    static public TodoTask makeTodo(String description) throws BareumException {
        if (description.length() == 0) {
            throw new BareumException("Todo description cannot be empty.");
        }
        return new TodoTask(false, description);
    }

    static public TodoTask makeTodo(String[] taskInputs) {
        boolean isDone = taskInputs[0].equals("1");
        return new TodoTask(isDone, taskInputs[2]);
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }

    @Override
    public String toSavedString() {
        String done = isDone ? "1" : "0";
        return "T|" + done + "|" + this.description + "\n";
    }
}