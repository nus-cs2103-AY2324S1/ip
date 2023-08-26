// package main.java;

public class Deadline extends Task {
    String[] split1;
    String[] split2;

    Deadline(String content) {
        super(content);
        split1 = this.content.split("/", 2);
        split2 = split1[1].split(" ", 2);
    }

    Deadline(String content, boolean status) {
        super(content, status);
        split1 = this.content.split("/", 2);
        split2 = split1[1].split(" ", 2);
    }

    Deadline mark() {
        return new Deadline(content, true);
    }

    Deadline unmark() {
        return new Deadline(content);
    }

    String addTask(int listSize) {
        return "____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                toString() + "\n" +
                String.format("Now you have %d tasks in the list,\n", listSize) +
                "____________________________________________________________";
    }

    public String toString() {
        String[] result = split1[0].split(" ", 2);
        if (!this.marked) {
            return String.format("[D][ ] %s(by: %s)", result[1], split2[1]);
        } else {
            return String.format("[D][X] %s(by: %s)", result[1], split2[1]);
        }
    }
}
