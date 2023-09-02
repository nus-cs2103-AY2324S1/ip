package duke;

public class Task {
    private final String desc;
    private boolean marked;

    public Task(String desc) {
        this.desc = desc;
        marked = false;
    }

    public static Task decode(String encodedTask) {
        String[] tokens = encodedTask.split("\\|", 3);
        String type = tokens[0];
        String mark = tokens[1];
        String input = tokens[2];
        Task task;
        switch (type) {
            case "T":
                task = new Todo(input);
                if (mark.equals("1")) {
                    task.mark();
                }
                break;
            case "D":
                String[] deadlineTokens = input.split("/by", 2);
                task = new Deadline(deadlineTokens[0], deadlineTokens[1]);
                break;
            case "E":
                String[] eventTokens = input.split("/from", 2);
                String[] eventTokens2 = eventTokens[1].split("/to", 2);
                task = new Event(eventTokens[0], eventTokens2[0], eventTokens2[1]);
                break;
            default:
                task = new Task("");
        }
        if (mark.equals("1")) {
            task.mark();
        }
        return task;
    }

    public void mark() {
        marked = true;
    }

    public void unmark() {
        marked = false;
    }

    public String encode() {
        return String.format("%s|%s", marked ? "1" : "0", desc);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", marked ? "X" : " ", desc);
    }
}
