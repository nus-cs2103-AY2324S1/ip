package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import dukeuielements.Ui;

/**
 * This class encapsulates a Task.
 */
public class Task {
    private String description;
    private boolean isTrueStatus;

    /**
     * Instantiates a new Task.
     *
     * @param description the description
     */
    public Task(String description) {
        this.description = description;
        this.isTrueStatus = false;
    }

    /**
     * Instantiates a new Task.
     *
     * @param description the description
     * @param status      the status
     */
    public Task(String description, boolean status) {
        this.description = description;
        this.isTrueStatus = status;
    }

    /**
     * Returns task complete/incomplete message based on user input.
     *
     * @param userInput mark/unmark which specifies the command.
     * @return the string
     */
    public String changeStatus(String userInput) throws DukeException {
        try {
            switch (userInput) {
            case "mark":
                return Ui.markStringReturn(this);
            case "unmark":
                return Ui.unmarkStringReturn(this);
            default:
                throw new DukeException("Unknown error occurred!");
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }




    /**
     * Returns date and time as a LocalDateTime parsed from user input yyyy-MM-dd HH:mm.
     *
     * @param userDateAndTime User provided date and time.
     * @return Parsed date and time in yyyy-MM-dd HH:mm.
     * @throws DateTimeParseException If user input does not follow required format.
     */
    public LocalDateTime formatDateAndTime(String userDateAndTime) throws DateTimeParseException {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(userDateAndTime, inputFormat);
    }

    /**
     * Returns date and time as a String parsed from LocalDateTime variable yyyy-MM-dd HH:mm.
     *
     * @param systemDateAndTime LocalDateTime value stored on disk.
     * @return String type Date and time in MMM dd, yyyy HH:mm format.
     */
    public String printDateTimeFormat(LocalDateTime systemDateAndTime) {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return systemDateAndTime.format(outputFormat);
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public int diskStatus() {
        return this.isTrueStatus ? 1 : 0;
    }
    public boolean getTaskStatus() {
        return this.isTrueStatus;
    }
    public void setFalseStatus() {
        this.isTrueStatus = !this.isTrueStatus;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a String to store Task on Duke.txt file.
     *
     * @return String to store Task on Duke.txt file.
     */
    public String storeToDiskFormat() {
        return this.diskStatus() + "|" + this.description;
    }

    @Override
    public String toString() {
        if (this.diskStatus() == 1) {
            return "[" + "X" + "] " + this.description;
        } else {
            return "[" + " " + "] " + this.description;
        }
    }
}
