package Task;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String dateTime;

    public Task(String taskDesc) {
        this.description = taskDesc;
        this.isDone = false;
    }

    public boolean checkIsDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public void isCompleted() {
        isDone = true;
    }

    public void isNotCompleted() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getTask() {
        return "[" + getTypeIcon() + "]";
    }

    public String getTypeIcon() {
        if (this instanceof Add) {
            return "A";
        } else if (this instanceof ToDo) {
            return "T";
        } else if (this instanceof DeadLine) {
            return "D";
        } else if (this instanceof Event) {
            return "E";
        }
        return "Error: No Such Task";
    }

    public String printDateTime(LocalDateTime dateTime) {
        DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        String formattedDate = dateTime.format(printFormat);
        return formattedDate;
    }

    public LocalDateTime parseDateTime(String dateTime) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");

        try {
            Date date = inputFormat.parse(dateTime);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
            String formattedDate = outputFormat.format(date);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
            LocalDateTime localDateTime = LocalDateTime.parse(formattedDate, formatter);
            return localDateTime;
        } catch (java.text.ParseException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return getTask() + getStatusIcon() + " " + description;
    }
}
