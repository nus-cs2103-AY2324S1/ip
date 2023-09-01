package task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.zip.DataFormatException;

public class Task {

    private String description;
    private boolean status;

    private String indent = "    ";
    public Task(String description) {
        this.description = description;
        this.status = false;
    }
    public Task(String description, boolean status) {
        this.description = description;
        this.status = status;
    }



    public String changeStatus(String userInput) {
        if (!this.status && userInput.equals("mark")) {
            this.status = !this.status;
            return "Nice! Task completed successfully!" + "\n" + indent + this.toString();
        } else if (!this.status && userInput.equals("unmark")) {
            return "Task already unmarked! Please try again..." + "\n" + indent + this.toString();
        } else if (this.status && userInput.equals("unmark")) {
            this.status = !this.status;
            return "Sure! Task status unchecked!" + "\n" + indent + this.toString();
        } else {
            return "Task already unchecked. Please try again..." + "\n" + indent + this.toString();
        }
    }

    public LocalDateTime formatDateAndTime(String userDateAndTime) throws DateTimeParseException {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(userDateAndTime, inputFormat);
    }
    public String printDateTimeFormat(LocalDateTime systemDateAndTime) {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return systemDateAndTime.format(outputFormat);
    }
    public int getStatus() {
        return this.status ? 1 : 0;
    }
    public String getDescription() {
        return this.description;
    }

    public void storeDescription(String description) {
        this.description = description;
    }

    public String storeToDiskFormat() {
        return this.getStatus() + "|" + this.description;
    }

    @Override
    public String toString() {
        if (this.getStatus() == 1) {
            return "[" + "X" + "] " + this.description;
        } else {
            return "[" + " " + "]" + this.description;
        }
    }
}
