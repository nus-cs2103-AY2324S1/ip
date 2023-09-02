package duke.utils;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Parser {
    private List<String> inputArray;
    public Parser() {

    }
    public Parser(String userInput) {
        this.inputArray = Arrays.asList(userInput.split(" "));
    }

    public String inputCommand() {
        return inputArray.get(0).toUpperCase();
    }

    public int getTaskNumber() throws DukeException {
        try {
            int taskIndex = Integer.parseInt(inputArray.get(1)) - 1;
            return taskIndex;
        } catch (NullPointerException e) {
            throw new DukeException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("invalid task number");
        }
    }

    public String getTodoDescription() throws DukeException {
        if (this.inputArray.size() <= 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        } else {
            String description = String.join(" ", this.inputArray.subList(1, this.inputArray.size()));
            return description;
        }
    }

    public String[] getDeadlineDescription() throws DukeException {
        if (this.inputArray.size() <= 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else {
            String description = String.join(" ", this.inputArray.subList(1, this.inputArray.size()));
            return description.split(" /by ");
        }
    }

    public String[] getEventDescription() throws DukeException{
        if (this.inputArray.size() <= 1) {
            throw new DukeException("The description of a event cannot be empty.");
        }
        String description = String.join(" ", this.inputArray.subList(1, this.inputArray.size()));
        String[] descriptionAndDateTimes = description.split(" /from ");
        if (descriptionAndDateTimes.length < 2) {
            throw new DukeException("Time and date required");
        }
        String[] fromAndToDateTimes = descriptionAndDateTimes[1].split(" /to ");
        if (fromAndToDateTimes.length < 2) {
            throw new DukeException("Time and date required");
        }
        return new String[] {descriptionAndDateTimes[0],fromAndToDateTimes[0], fromAndToDateTimes[1]};
    }

    public LocalDateTime getDateTime(String dateTime) throws DukeException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Sorry! Incorrect format for time and date :(");
        }
    }

    public String getStringKeyword() throws DukeException {
        try {
            return String.join(" ", this.inputArray.subList(1, this.inputArray.size()));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
