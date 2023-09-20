package emiya.parser;

import static emiya.logic.Logic.enumContainsKeyword;
import static emiya.logic.Logic.isInvalidDateTime;
import static emiya.logic.Logic.isWrongDateFormat;
import static emiya.logic.Logic.isWrongTimeFormat;

import emiya.emiyaexception.InvalidDateTimeException;
import emiya.emiyaexception.NoByException;
import emiya.emiyaexception.NoFromException;
import emiya.emiyaexception.NoToException;
import emiya.emiyaexception.UnknownCommandException;
import emiya.emiyaexception.WrongDateTimeFormatException;


/**
 * A class that takes in a String input from the user and parses it to determine what command
 * the user has given.
 */
public class Parser {

    String[] parsedInput;
    public Parser() {
        this.parsedInput = null;
    }

    /**
     * Breaks down the input into smaller, separate strings to determine what command the user wished to run.
     * @param input A String input that is taken in from the user. Will be parsed to determine what command
     *              to execute.
     * @return A String array that contains the commands, as well as details needed for the command.
     * @throws UnknownCommandException When a user gives a command that is unknown.
     */
    public String[] parseToRemoveUnknownCommands(String input) throws UnknownCommandException {
        // this part splits the input into 2 parts, depending on whitespace (if possible)
        String[] partsOfInput = input.split("\\s+", 2);

        if (partsOfInput.length == 1) {
            partsOfInput = new String[] {partsOfInput[0] , ""};
        }

        boolean isNotAKeyword = !enumContainsKeyword(partsOfInput[0].toUpperCase());

        // if input is a single word AND it is not a keyword, throw UnknownCommandException
        if (isNotAKeyword) {
            throw new UnknownCommandException();
        }

        String typeOfTask = partsOfInput[0];
        String taskDetails = partsOfInput[1];

        this.parsedInput = new String[] {typeOfTask, taskDetails};

        return this.parsedInput;
    }

    /**
     * Parses the details of the deadline task in order to determine
     * important details of the deadline task, such as when the task has to be done
     * by.
     * @param taskDetails A String that contains details about the deadline task, such as when the
     *                    task has to be finished by.
     * @return A String array that contains the input from the user parsed into sections
     *     that can be understood by the rest of the program.
     * @throws NoByException When the user does not use the keyword /by in his input.
     * @throws UnknownCommandException An exception that is thrown when an unknown command is given by the user.
     */
    public String[] parseForDeadline(String taskDetails) throws NoByException, UnknownCommandException {
        String[] deadlineDetails = taskDetails.split(" /by ", 2);
        if (!taskDetails.contains("/by")) {
            throw new NoByException();
        }
        if (deadlineDetails.length <= 1) {
            throw new UnknownCommandException();
        }
        this.parsedInput = deadlineDetails;
        return deadlineDetails;
    }

    /**
     * Parses the details of the deadline task in order to determine
     * important details of the deadline task, such as when the task has to be done
     * by.
     * @param taskDetails A String that contains details about the deadline task, such as when the
     *                    event starts and when it ends.
     * @return A String array that contains the input from the user parsed into sections
     *     that can be understood by the rest of the program.
     * @throws NoToException When the user does not use the keyword /to in his input.
     * @throws NoFromException When the user does not use the keyword /from in his input.
     */
    public String[] parseForEvent(String taskDetails) throws NoToException, NoFromException, UnknownCommandException {
        String[] eventDetails = taskDetails.split(" /from ", 2);
        if (!taskDetails.contains("/from")) {
            throw new NoFromException();
        }
        if (!taskDetails.contains("/to")) {
            throw new NoToException();
        }
        if (eventDetails.length <= 1) {
            throw new UnknownCommandException();
        }
        String[] eventDurationDetails = eventDetails[1].split(" /to ", 2);
        if (eventDurationDetails.length <= 1) {
            throw new UnknownCommandException();
        }
        this.parsedInput = new String[] {eventDetails[0], eventDurationDetails[0], eventDurationDetails[1]};
        return this.parsedInput;
    }

    /**
     * Parses input into String objects that can be understood by the rest of the program.
     * @param input A String input that represents a certain date and time.
     * @return A String array that contains sections that can be understood by the rest of the program.
     * @throws InvalidDateTimeException When the date given is invalid.
     * @throws WrongDateTimeFormatException When the date given is in the wrong format.
     */
    public static String[] parseForDate(String input) throws InvalidDateTimeException, WrongDateTimeFormatException {
        String[] partsOfDateTime = input.split("\\s+", 2);

        // word/no whitespace used
        if (partsOfDateTime.length <= 1) {
            throw new WrongDateTimeFormatException();
        }

        String datePart = partsOfDateTime[0];
        String timePart = partsOfDateTime[1];
        String[] partsOfDate = datePart.split("-", 3);

        // if second part is not the time in 24h format/date not given in correct format
        if (isWrongTimeFormat(timePart) || isWrongDateFormat(partsOfDate)) {
            throw new WrongDateTimeFormatException();
        }

        int year = Integer.parseInt(partsOfDate[0]);
        int month = Integer.parseInt(partsOfDate[1]);
        int day = Integer.parseInt(partsOfDate[2]);
        int hour = Integer.parseInt(timePart.substring(0, 2));
        int min = Integer.parseInt(timePart.substring(2, 4));

        if (isInvalidDateTime(year, month, day, hour, min)) {
            throw new InvalidDateTimeException();
        }

        return new String[] {datePart, hour < 10 ? "0" + hour : hour + "", min < 10 ? "0" + min : min + ""};
    }
}
