import java.time.LocalDate;


public class Task {
    private String description;
    private boolean isDone;
    private LocalDate date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDate(String input) {
        this.date = LocalDate.parse(input);
    }
    public LocalDate getDate() {
        return this.date;
    }
    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }
    public String printDate() {
        int day = this.date.getDayOfMonth();
        String month =  this.date.getMonth().toString().substring(0, 3);
        int year = this.date.getYear();
        return day + " " + month + " " + year;
    }
    public String toStorageString() {
        return "  // " + this.getStatusIcon() + " // " + this.description;
    }

    @Override
    public String toString() {
        return "[ ]" + "[" + this.getStatusIcon() + "] " + this.description;
    }


}
