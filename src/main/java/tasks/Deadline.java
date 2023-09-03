package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    String[] split1;
    String[] split2;
    LocalDate dueDate;

    /* public Deadline(String content) {
        super(content);
        split1 = content.split("/", 2);
        split2 = split1[1].split(" ", 2);
    } */
    public Deadline(String content, boolean status) {
        super(content, status);
        split1 = content.split("/", 2);
        split2 = split1[1].split(" ", 2);
        try {
            this.dueDate = LocalDate.parse(split2[1].replace("/", "-"));
        } catch (DateTimeParseException e) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                this.dueDate = LocalDate.parse(split2[1], formatter);
            } catch (DateTimeException ex) {
                throw new IllegalArgumentException("Wrong inputs"); // unable to throw DukeException
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
            return String.format("[D][ ] %s(by: %s)", result[1], split2[1]);
        } else {
            return String.format("[D][X] %s(by: %s)", result[1], split2[1]);

        }
    }
}
