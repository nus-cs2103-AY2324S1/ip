package tasks;

public class Deadline extends Task {
    String[] split1;
    String[] split2;

    public Deadline(String content) {
        super(content);
        split1 = content.split("/", 2);
        split2 = split1[1].split(" ", 2);
    }

    public Deadline(String content, boolean status) {
        super(content, status);
        split1 = content.split("/", 2);
        split2 = split1[1].split(" ", 2);
    }

    public Deadline mark() {
        return new Deadline(super.getContent(), true);
    }

    public Deadline unmark() {
        return new Deadline(super.getContent());
    }

    public String addTask(int listSize) {
        return "____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                toString() + "\n" +
                String.format("Now you have %d tasks in the list,\n", listSize) +
                "____________________________________________________________";
    }

    public String toString() {
        String[] result = split1[0].split(" ", 2);
        if (!super.isMarked()) {
            return String.format("[D][ ] %s(by: %s)", result[1], split2[1]);
        } else {
            return String.format("[D][X] %s(by: %s)", result[1], split2[1]);
        }
    }
}
