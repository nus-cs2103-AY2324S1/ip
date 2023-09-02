package emiya.parser;

import static emiya.logic.Logic.enumContainsKeyword;

import emiya.emiyaexception.InvalidDateException;
import emiya.emiyaexception.NoByException;
import emiya.emiyaexception.NoFromException;
import emiya.emiyaexception.NoToException;
import emiya.emiyaexception.UnknownCommandException;
import emiya.emiyaexception.WrongDateFormatException;
import emiya.logic.Logic;


/**
 * A class that takes in a String input from the user and parses it to determine what command
 * the user has given.
 */
public class Parser {
    public Parser() {

    }

    /**
     * A method that takes in the initial input from the user to determine the type of command
     * the user wishes to run. The method breaks down the input into smaller, separate strings to
     * achieve this.
     * @param position An Integer object that is passed in to determine what position needs to be changed
     *                 for certain commands, such as mark and unmark.
     * @param input A String input that is taken in from the user. Will be parsed to determine what command
     *              to execute.
     * @return A String array that contains the commands, as well as details needed for the command.
     * @throws UnknownCommandException When a user gives a command that is unknown.
     */
    public String[] parseToRemoveUnknownCommands(Integer[] position, String input) throws UnknownCommandException {
        // this part splits the input into 2 parts, depending on whitespace (if possible)
        String[] partsOfInput = input.split("\\s+", 2);

        // if input is a single word AND it is not a keyword, throw UnknownCommandException
        if (partsOfInput.length < 2 && !enumContainsKeyword(partsOfInput[0].toUpperCase())) {
            throw new UnknownCommandException();
        }

        // figure out what command is being input
        String typeOfTask = partsOfInput[0];
        // if it is a mark/unmark, this will be number, else is part of command
        String taskDetails = "";
        if (partsOfInput.length > 1) {
            taskDetails = partsOfInput[1];
        }

        // for mark/unmark commands
        if (Logic.isNumeric(taskDetails)) {
            position[0] = Integer.parseInt(taskDetails);
            return new String[] {typeOfTask};
        } else { // any other command, need pass in task details as well
            return new String[] {typeOfTask, taskDetails};
        }
    }

    /**
     * A method that parses the details of the deadline task in order to determine
     * important details of the deadline task, such as when the task has to be done
     * by.
     * @param taskDetails A String that contains details about the deadline task, such as when the
     *                    task has to be finished by.
     * @return A String array that contains the input from the user parsed into sections
     * that can be understood by the rest of the program.
     * @throws NoByException When the user does not use the keyword /by in his input.
     */
    public String[] parseForDeadline(String taskDetails) throws NoByException {
        String[] deadlineDetails = taskDetails.split(" /by ", 2);
        if (deadlineDetails.length <= 1) {
            throw new NoByException();
        }
        return deadlineDetails;
    }

    /**
     * A method that parses the details of the deadline task in order to determine
     * important details of the deadline task, such as when the task has to be done
     * by.
     * @param taskDetails A String that contains details about the deadline task, such as when the
     *                    event starts and when it ends.
     * @return A String array that contains the input from the user parsed into sections
     * that can be understood by the rest of the program.
     * @throws NoToException When the user does not use the keyword /to in his input.
     * @throws NoFromException When the user does not use the keyword /from in his input.
     */
    public String[] parseForEvent(String taskDetails) throws NoToException, NoFromException {
        String[] eventDetails = taskDetails.split(" /from ", 2);
        if (eventDetails.length <= 1) {
            throw new NoFromException();
        }
        String[] eventDurationDetails = eventDetails[1].split(" /to ", 2);
        if (eventDurationDetails.length <= 1) {
            throw new NoToException();
        }
        return new String[] {eventDetails[0], eventDurationDetails[0], eventDurationDetails[1]};
    }

    /**
     * A static method that takes in a String that contains the date information about the task, and
     * parses it into sections that can be understood by the rest of the program.
     * @param input A String input that represents a certain date and time.
     * @return A String array that contains sections that can be understood by the rest of the program.
     * @throws InvalidDateException When the date given is invalid.
     * @throws WrongDateFormatException When the date given is in the wrong format.
     */
    public static String[] parseForDate(String input) throws InvalidDateException, WrongDateFormatException {
        String[] partsOfDateTime = input.split("\\s+", 2);

        // word/no whitespace used
        if (partsOfDateTime.length <= 1) {
            throw new WrongDateFormatException();
        }

        String datePart = partsOfDateTime[0];
        String timePart = partsOfDateTime[1];
        String[] partsOfDate = datePart.split("-", 3);

        // if second part is not the time in 24h format/date not given in correct format
        if (timePart.length() != 4 || !Logic.isNumeric(timePart.substring(0, 2))
                || !Logic.isNumeric(timePart.substring(2, 4)) || partsOfDate.length < 3
                || !Logic.isNumeric(partsOfDate[0]) || !Logic.isNumeric(partsOfDate[1])
                || !Logic.isNumeric(partsOfDate[2])) {
            throw new WrongDateFormatException();
        }

        int year = Integer.parseInt(partsOfDate[0]);
        int month = Integer.parseInt(partsOfDate[1]);
        int day = Integer.parseInt(partsOfDate[2]);
        int hour = Integer.parseInt(timePart.substring(0, 2));
        int min = Integer.parseInt(timePart.substring(2, 4));

        // if given date is invalid
        if (!Logic.isValidYear(year) || !Logic.isValidMonth(month)
                || !Logic.isValidDay(day) || !Logic.isValidHour(hour)
                || !Logic.isValidMinute(min)) {
            throw new InvalidDateException();
        }

        return new String[] {datePart, hour < 10 ? "0" + hour : hour + "", min < 10 ? "0" + min : min + ""};

    }
}
