public class TodoTask extends Task {

    private TodoTask(boolean isDone, String description) {
        super(isDone, description);
    }

    static public TodoTask makeTodo(String description) throws IllegalArgumentException {
        if (description.length() == 0) {
            throw new IllegalArgumentException("Todo description cannot be empty.");
        }
        return new TodoTask(false, description);
    }

    static public TodoTask makeTodo(String[] taskInputs) throws IllegalArgumentException {
        if (taskInputs.length != 3) {
            // make new exception for this
            throw new IllegalArgumentException("Wrong number of arguments. " +
                    "Current number of arguments: " + taskInputs.length);
        }
        if (taskInputs[2].length() == 0) {
            throw new IllegalArgumentException("Todo description cannot be empty.");
        }
        boolean isDone = taskInputs[0].equals("1");
        return new TodoTask(isDone, taskInputs[2]);
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "]" + this.description;
    }

    @Override
    public String toSavedString() {
        String done = isDone ? "1" : "0";
        return "T|" + done + "|" + this.description + "\n";
    }
}