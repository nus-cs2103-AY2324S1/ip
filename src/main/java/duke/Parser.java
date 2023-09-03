package duke;

import duke.command.MarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.command.DeadlineCommand;
import duke.command.TodoCommand;
import duke.command.EventCommand;

import java.time.LocalDateTime;

/**
 * Helps to break down the user input and determines what command objects to create
 */
public class Parser {

    private static LocalDateTime convertInputDateAndTimeIntoLocalDateTime(String input) throws RichieException {
        try {
            String stringDateTime = input;
            String[] dateTimeArray = stringDateTime.split(" ", 2);
            String stringDate = dateTimeArray[0];
            String stringTime = dateTimeArray[1];
            String[] dayMonthYearArray = stringDate.split("/", 3);
            LocalDate date = LocalDate.of(Integer.parseInt(dayMonthYearArray[2]), Integer.parseInt(dayMonthYearArray[1]),
                    Integer.parseInt(dayMonthYearArray[0]));
            LocalTime time = LocalTime.of(Integer.parseInt(stringTime.substring(0, 2)), Integer.parseInt(stringTime.substring(2, 4)));
            LocalDateTime dateTime = LocalDateTime.of(date, time);
            return dateTime;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RichieException("Date and Time entered is in the wrong format!");
        } catch (DateTimeException e) {
            throw new RichieException("Date and Time entered is in the wrong format!");
        }

    }

    public static Command parse(String userInput) throws RichieException {
        if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else {
            String[] stringArray = userInput.split(" ", 2);
            if (stringArray[0].equals("mark")) {
                try {
                    int taskIndex = Integer.parseInt(stringArray[1]);
                    return new MarkCommand(taskIndex);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new RichieException("Incomplete input, please specify which task to mark");
                } catch (NumberFormatException e) {
                    throw new RichieException("Invalid input, please enter a number");
                }
            } else if (stringArray[0].equals("delete")) {
                try {
                    int taskIndex = Integer.parseInt(stringArray[1]);
                    return new DeleteCommand(taskIndex);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new RichieException("Incomplete input, please specify which task to delete");
                } catch (NumberFormatException e) {
                    throw new RichieException("Invalid input, please enter a number");
                }
            } else if (stringArray[0].equals("deadline")) {
                try {
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
            } else if (stringArray[0].equals("todo")) {
                try {
                    if (!stringArray[1].equals("") && !stringArray[1].equals(" ")) {
                        return new TodoCommand(stringArray[1]);
                    } else {
                        throw new RichieException("OOPS!! The todo description is empty!");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new RichieException("OOPS!! The todo description is empty!");
                }
            } else if (stringArray[0].equals("event")) {
                try {
                    if (!stringArray[1].contains("/from")) {
                        throw new RichieException("OOPS!! please enter '/from' followed by a date and " +
                                "time that the task should start from");
                    } else if (!stringArray[1].contains("/to")) {
                        throw new RichieException("OOPS!! please enter '/to' followed by a date and " +
                                "time that the task should end");
                    } else if (stringArray[1].split("/from", 2)[0].equals("")) {
                        throw new RichieException("OOPS!! The description of a event or the duration of the event is incomplete");
                    } else if (!stringArray[1].contains(" /from ") || !stringArray[1].contains(" /to ")) {
                        throw new RichieException("OOPS!! please ensure that there are spaces before and after '/from' and '/to'");
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
            } else {
                throw new RichieException("No command detected, please ensure that you leave a space after command word");
            }
        }
    }
}