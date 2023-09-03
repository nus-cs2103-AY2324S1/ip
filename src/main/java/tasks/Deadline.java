package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    String[] split1;
    String[] split2;
    String dueDate;

    public Deadline(String content, boolean status) {
        super(content, status);
        split1 = content.split("/", 2);
        split2 = split1[1].split(" ", 3);
        try {
            this.dueDate = LocalDate.parse(split2[1].replace("/", "-")).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                this.dueDate = LocalDate.parse(split2[1], formatter).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeException ex) {
                this.dueDate = split1[1];
            }
        }
    }

    public Deadline mark() {
        return new Deadline(super.getContent(), true);
    }

    public Deadline unmark() {
        return new Deadline(super.getContent(), false);
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
            return String.format("[D][ ] %s(by: %s)", result[1], dueDate);
        } else {
            return String.format("[D][X] %s(by: %s)", result[1], dueDate);
        }
    }
    // commit for Event Level-8
}
