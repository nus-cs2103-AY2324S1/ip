public class Task {
    private Boolean done = false;

    private String task;

    public Task(String input) {
        task = input;
    }

    private String type;

    private String desc;

    private String checkBox;

    private String deadline;

    public static Task correctTask(String type, String completion, String item, String deadline) {
        if (type.equals("[T] ")) {
            //System.out.println("todo " +  item);
            Task t = new ToDoTask(item);
            if (completion.equals("[X] ")) {
                t.markDone();
            }
            return t;
        } else if (type.equals("[E] ")) {
            String from = deadline.split("-")[0];
            String to = deadline.split("-")[1];
            Task t = new EventTask(item.trim() + " /from " + from.trim() + " /to " + to.trim());
            if (completion.equals("[X] ")) {
                t.markDone();
            }
            return t;
        } else {
            Task t = new DeadlineTask(item.trim() + " /by " + deadline.trim());
            if (completion.equals("[X] ")) {
                t.markDone();
            }
            return t;
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
