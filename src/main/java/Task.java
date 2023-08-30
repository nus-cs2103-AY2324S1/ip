public class Task {
    private Boolean done;

    private String task;

    public Task(String input) {
        task = input;
        done = false;
    }

    private String type;

    private String desc;

    private String checkBox;

    private String deadline;

    public Task(String type, String item, String completion, String deadline) {
        if (type.equals("[T]")) {
            Task t = new ToDoTask("todo " +  item);
            if (completion.equals("[X]")) {
                t.markDone();
            }
        } else if (type.equals("[E]")) {
            Task t = new EventTask("event " + item + " /" + deadline);
            if (completion.equals("[X]")) {
                t.markDone();
            }
        } else {
            Task t = new DeadlineTask("deadline " + item + " /" + deadline);
            if (completion.equals("[X]")) {
                t.markDone();
            }
        }
    }

    public void markDone() {
        done = true;
    }

    public void markUndone() {
        done = false;
    }

    protected boolean isDone() {
        return done;
    }

    public String toString() {
        if (done) {
            return "[X] " + "| " + task;
        } else {
            return "[ ] " +"| " + task;
        }
    }

}
