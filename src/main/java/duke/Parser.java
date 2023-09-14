package duke;

import duke.command.MarkCommand;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.command.DeadlineCommand;
import duke.command.TodoCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

import java.lang.AssertionError;

/**
 * Helps to break down the user input and determines what command objects to create
 */
public class Parser {

    private static LocalDateTime convertInputDateAndTimeIntoLocalDateTime(String input) throws RichieException {
        // input is in the form of 2/12/2002 0400
        try {
            // Separates the date from the time
            String[] dateTimeArray = input.split(" ", 2);
            String stringDate = dateTimeArray[0];
            String stringTime = dateTimeArray[1];
            // Separates the date into day, month and year, day being in index 0, month in index 1 and year in index 2
            // of the dayMonthYearArray
            String[] dayMonthYearArray = stringDate.split("/", 3);
            int year = Integer.parseInt(dayMonthYearArray[2]);
            int month = Integer.parseInt(dayMonthYearArray[1]);
            int day = Integer.parseInt(dayMonthYearArray[0]);
            LocalDate date = LocalDate.of(year, month, day);
            int hours = Integer.parseInt(stringTime.substring(0, 2));
            int minutes = Integer.parseInt(stringTime.substring(2, 4));
            LocalTime time = LocalTime.of(hours, minutes);
            LocalDateTime dateTime = LocalDateTime.of(date, time);
            return dateTime;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RichieException("Date and Time entered is in the wrong format!");
        } catch (DateTimeException e) {
            throw new RichieException("Date and Time entered is in the wrong format!");
        }

    }

    /**
     * Helps to decode the user input string and decides what Command object to return based on the user input.
     * It will also throw the correct errors depending on the user input
     * @param userInput String that the user entered
     * @return A Command object depending on what command was called by the user
     * @throws RichieException Gives useful exception messages depending on the user input
     */
    public static Command parse(String userInput) throws RichieException {
        if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.contains("mark")) {
            if (!userInput.substring(0, 5).equals("mark ")) {
                throw new RichieException("mark command should be at the front of the command followed by a space");
            }
            try {
                // split into mark, and task index to mark elements in stringArray
                String[] stringArray = userInput.split(" ", 2);
                int taskIndex = Integer.parseInt(stringArray[1]);
                return new MarkCommand(taskIndex);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new RichieException("Incomplete input, please specify which task to mark");
            } catch (NumberFormatException e) {
                throw new RichieException("Invalid input, please enter a number");
            }
        } else if (userInput.contains("delete")) {
            if (!userInput.substring(0, 7).equals("delete ")) {
                throw new RichieException("delete command should be at the front of the command followed by a space");
            }
            try {
                // split into delete, and task index to delete elements in stringArray
                String[] stringArray = userInput.split(" ", 2);
                int taskIndex = Integer.parseInt(stringArray[1]);
                return new DeleteCommand(taskIndex);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new RichieException("Incomplete input, please specify which task to delete");
            } catch (NumberFormatException e) {
                throw new RichieException("Invalid input, please enter a number");
            }
        } else if (userInput.contains("deadline")) {
            if (!userInput.substring(0, 9).equals("deadline ")) {
                throw new RichieException("deadline command should be at the front of the command followed by a space");
            }
            try {
                String[] stringArray = userInput.split(" ", 2);
                if (!stringArray[1].contains("/by")) {
                    throw new RichieException("OOPS!! please enter '/by' followed by a date and " +
                            "time that the task should be done by");
                } else if (stringArray[1].split("/by", 2)[0].equals("")) {
                    throw new RichieException("OOPS!! Either the description or the deadline is empty!");
                } else if (!stringArray[1].contains(" /by ")) {
                    throw new RichieException("OOPS!! please ensure that there are spaces before and after '/by'");
                }
                String[] stringArray2 = stringArray[1].split(" /by ", 2);
                LocalDateTime dateTime = convertInputDateAndTimeIntoLocalDateTime(stringArray2[1]);
                return new DeadlineCommand(stringArray2[0], dateTime);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new RichieException("OOPS!! Either the description or the deadline is empty!");
            }
        } else if (userInput.contains("todo")) {
            if (!userInput.substring(0, 5).equals("todo ")) {
                throw new RichieException("todo command should be at the front of the command followed by a space");
            }
            try {
                String[] stringArray = userInput.split(" ", 2);
                if (!stringArray[1].equals("") && !stringArray[1].equals(" ")) {
                    return new TodoCommand(stringArray[1]);
                } else {
                    throw new RichieException("OOPS!! The todo description is empty!");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new RichieException("OOPS!! The todo description is empty!");
            }
        } else if (userInput.contains("event")) {
            if (!userInput.substring(0, 6).equals("event ")) {
                throw new RichieException("todo command should be at the front of the command followed by a space");
            }
            try {
                String[] stringArray = userInput.split(" ", 2);
                if (!stringArray[1].contains("/from")) {
                    throw new RichieException("OOPS!! please enter '/from' followed by a date and "
                            + "time that the task should start from");
                } else if (!stringArray[1].contains("/to")) {
                    throw new RichieException("OOPS!! please enter '/to' followed by a date and "
                            + "time that the task should end");
                } else if (stringArray[1].split("/from", 2)[0].equals("")) {
                    throw new RichieException("OOPS!! The description of a event or the duration of the event "
                            + "is incomplete");
                } else if (!stringArray[1].contains(" /from ") || !stringArray[1].contains(" /to ")) {
                    throw new RichieException("OOPS!! please ensure that there are spaces before and after "
                            + "'/from' and '/to'");
                }
                String[] stringArray2 = stringArray[1].split(" /from ", 2);
                String[] stringArray3 = stringArray2[1].split(" /to ", 2);
                String stringFromDateTime = stringArray3[0];
                String stringToDateTime = stringArray3[1];
                return new EventCommand(stringArray2[0], convertInputDateAndTimeIntoLocalDateTime(stringFromDateTime),
                        convertInputDateAndTimeIntoLocalDateTime(stringToDateTime));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new RichieException("OOPS!! The description of a event or the duration of the event is incomplete");
            }
        } else if (userInput.contains("find")) {
            if (!userInput.substring(0, 5).equals("find ")) {
                throw new RichieException("find command should be at the front of the command followed by a space");
            }
            try {
                String[] stringArray = userInput.split(" ", 2);
                String keyword = stringArray[1];
                return new FindCommand(keyword);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new RichieException("Incomplete input, please specify what keyword to find");
            }
        } else {
                throw new RichieException("No command detected, please ensure that you leave a space after command word");
        }
    }
}
