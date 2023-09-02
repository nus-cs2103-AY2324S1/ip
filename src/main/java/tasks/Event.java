package tasks;

public class Event extends Task {
    String[] split1;
    String[] splitStart;
    String[] splitEnd;

    public Event(String content) {
        super(content);
        split1 = content.split("/", 3);
        splitStart = split1[1].split(" ", 2);
        splitEnd = split1[2].split(" ", 2);
    }

    public Event(String content, boolean status) {
        super(content, status);
        split1 = content.split("/", 3);
        splitStart = split1[1].split(" ", 2);
        splitEnd = split1[2].split(" ", 2);
    }

    public Event mark() {
        return new Event(super.getContent(), true);
    }

    public Event unmark() {
        return new Event(super.getContent());
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
            return String.format("[E][ ] %s(%s: %s%s: %s)", result[1], splitStart[0], splitStart[1], splitEnd[0], splitEnd[1]);
        } else {
            return String.format("[E][X] %s(%s: %s%s: %s)", result[1], splitStart[0], splitStart[1], splitEnd[0], splitEnd[1]);
        }
    }
}
