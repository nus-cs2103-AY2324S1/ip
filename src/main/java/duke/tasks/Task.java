package duke.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private String type;
    private String description;
    private boolean isDone = false;

    public Task(String description, String type, boolean isDone) {
        this.description = description;
        this.type = type;
        this.isDone = isDone;
    }

    abstract String getOriginalMessage();

    public void mark(boolean val) {
        this.isDone = val;
    }

    public void save(String filepath) {
        try {
            FileWriter myWriter = new FileWriter(filepath, true);
            myWriter.write(String.format("%s%s\n", this.getOriginalMessage(), this.isDone ? "1" : "0"));
            myWriter.close();
        } catch (IOException ex) {
            System.out.println("    Error saving to file");
            System.exit(1);
        }
    }

    public String stringifyDate(LocalDateTime datetime) {
        String formatted = datetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm"));
        return String.join(" ", formatted.split("T"));
    }

    public String formatDate(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern("HHmm, MMM d yyyy"));
    }

    public String getDescription() {
        return this.description;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); //return tick or X symbols
    }

    @Override
    public String toString() {
        String s = String.format("[%s][%s] %s", this.type.substring(0,1).toUpperCase(), this.getStatusIcon(), this.description);
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof Task) {
            Task t = (Task) o;
            return this.isDone == t.isDone && this.description.equals(t.description);
        }

        return false;
    }
}
