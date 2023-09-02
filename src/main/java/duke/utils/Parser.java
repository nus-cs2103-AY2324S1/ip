package duke.utils;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * The `Parser` class is responsible for parsing user input and extracting relevant information
 * for further processing by the Duke chatbot.
 */
public class Parser {
    private List<String> inputArray;

    /**
     * Constructs a new `Parser` with an empty input array.
     */

    public Parser() {

    }

    /**
     * Constructs a new `Parser` with user input and splits it into an array of words.
     * @param userInput The user input string to be parsed.
     */
    public Parser(String userInput) {
        this.inputArray = Arrays.asList(userInput.split(" "));
    }

    /**
     * Extracts the command keyword from the user input.
     * @return The extracted command keyword in uppercase.
     */
    public String inputCommand() {
        return inputArray.get(0).toUpperCase();
    }

    /**
     * Extracts the command keyword from the user input.
     * @return The extracted command keyword in uppercase.
     */
    public int getTaskNumber() throws DukeException {
        try {
            int taskIndex = Integer.parseInt(inputArray.get(1)) - 1;
            return taskIndex;
        } catch (NullPointerException e) {
            throw new DukeException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number");
        }
    }

    /**
     * Retrieves the description of a todo task from the user input.
     * @return The description of the todo task.
     * @throws DukeException If the description is empty.
     */
    public String getTodoDescription() throws DukeException {
        if (this.inputArray.size() <= 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        } else {
            String description = String.join(" ", this.inputArray.subList(1, this.inputArray.size()));
            return description;
        }
    }

    /**
     * Retrieves the description and deadline date/time from the user input for a deadline task.
     * @return An array containing the description and deadline date/time.
     * @throws DukeException If the description is empty or if the date/time format is incorrect.
     */
    public String[] getDeadlineDescription() throws DukeException {
        if (this.inputArray.size() <= 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else {
            String description = String.join(" ", this.inputArray.subList(1, this.inputArray.size()));
            return description.split(" /by ");
        }
    }

    /**
     * Retrieves the description and event date/times from the user input for an event task.
     * @return An array containing the description, start date/time, and end date/time.
     * @throws DukeException If the description is empty, if time and date are missing, or if the format is incorrect.
     */
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

    /**
     * Converts a date/time string to a LocalDateTime object.
     * @param dateTime The date/time string to be converted.
     * @return A LocalDateTime object representing the parsed date/time.
     * @throws DukeException If the date/time format is incorrect.
     */
    public LocalDateTime getDateTime(String dateTime) throws DukeException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Sorry! Incorrect format for time and date :(");
        }
    }
}
