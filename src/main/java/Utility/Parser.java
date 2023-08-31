package Utility;

import java.time.LocalDate;
import java.time.LocalTime;

import Commands.*;
import Exceptions.DukeException;
import Exceptions.IncorrectFormatException;
import Exceptions.InvalidCommandException;
import Exceptions.InvalidNumberException;

public class Parser {

  public Parser() {
  };

  protected static class DateTimeWrapper {
    protected LocalDate date;
    protected LocalTime time;

    protected DateTimeWrapper(LocalDate date, LocalTime time) {
      this.date = date;
      this.time = time;
    }
  }

  private static String[] inputParser(String input) {
    int index = input.indexOf(' ');
    if (index > -1) {
      return input.split(" ", 2);
    } else {
      String[] tempString = { input, "" };
      return tempString;
    }
  }

  public static String[] filePathParser(String input) {
    int index = input.indexOf('/');
    if (index > -1) {
      return input.split("/", 2);
    } else {
      String[] tempString = { input, "" };
      return tempString;
    }
  }

  private static int indexParser(String input) throws DukeException {
    try {
      return Integer.parseInt(input) - 1;
    } catch (NumberFormatException e) {
      throw new InvalidNumberException();
    }
  }

  private static DateTimeWrapper dateParser(String input) {
    String[] parsedInput = inputParser(input);
    LocalDate date;
    LocalTime time;
    String i1 = parsedInput[0].trim();
    String i2 = parsedInput[1].trim();

    if (i1.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
      date = LocalDate.parse(i1.substring(6, 10) + "-" + i1.substring(3, 5) + "-" + i1.substring(0, 2));
    } else if (i2.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
      date = LocalDate.parse(i2.substring(6, 10) + "-" + i2.substring(3, 5) + "-" + i2.substring(0, 2));
    } else if (i1.matches("[0-9]{4}.[0-9]{2}.[0-9]{2}")) {
      System.err.println("b");
      date = LocalDate.parse(i1.substring(0, 4) + "-" + i1.substring(5, 7) + "-" + i1.substring(8, 10));
    } else if (i2.matches("[0-9]{4}.[0-9]{2}.[0-9]{2}")) {
      date = LocalDate.parse(i2.substring(0, 4) + "-" + i2.substring(5, 7) + "-" + i2.substring(8, 10));
    } else {
      date = null;
    }

    if (i1.matches("[0-9]{2}.[0-9]{2}.[0-9]{2}")) {
      time = LocalTime.parse(i1.substring(0, 2) + ":" + i1.substring(3, 5) + ":" + i1.substring(6, 8));
    } else if (i2.matches("[0-9]{2}.[0-9]{2}.[0-9]{2}")) {
      time = LocalTime.parse(i2.substring(0, 2) + ":" + i2.substring(3, 5) + ":" + i2.substring(6, 8));
    } else if (i1.matches("[0-9]{2}.[0-9]{2}")) {
      time = LocalTime.parse(i1.substring(0, 2) + ":" + i1.substring(3, 5) + ":00");
    } else if (i2.matches("[0-9]{2}.[0-9]{2}")) {
      time = LocalTime.parse(i2.substring(0, 2) + ":" + i2.substring(3, 5) + ":00");
    } else {
      time = null;
    }

    return new DateTimeWrapper(date, time);
  }

  public static ToDoCommand todoParser(String input, boolean doneness) throws IncorrectFormatException {
    if (input.equals("")) {
      throw new IncorrectFormatException();
    } else {
      return new ToDoCommand(input, doneness);
    }
  }

  public static DeadlineCommand deadlineParser(String input, boolean doneness) throws IncorrectFormatException {
    int index = input.indexOf(" /by ");

    if (index > -1) {
      String[] parsedInput = input.split(" /by ", 2);

      try {
        String description = parsedInput[0];
        DateTimeWrapper dates = dateParser(parsedInput[1]);
        LocalDate byDate = dates.date;
        LocalTime byTime = dates.time;

        return new DeadlineCommand(description, doneness, byDate, byTime);
      } catch (Exception e) {
        System.err.println(e.getMessage());
        throw new IncorrectFormatException();
      }
    } else {
      throw new IncorrectFormatException();
    }
  }

  public static EventCommand eventParser(String input, boolean doneness) throws IncorrectFormatException {
    int indexFrom = input.indexOf(" /from ");
    int indexTo = input.indexOf(" /to ");

    if (indexFrom > -1 && indexTo > -1) {
      String[] parsedInput = { input.substring(0, indexFrom), input.substring(indexFrom + 7, indexTo),
          input.substring(indexTo + 5, input.length()) };

      try {
        String description = parsedInput[0];
        DateTimeWrapper fromDateTime = dateParser(parsedInput[1]);
        DateTimeWrapper toDateTime = dateParser(parsedInput[2]);
        LocalDate fromDate = fromDateTime.date;
        LocalTime fromTime = fromDateTime.time;
        LocalDate toDate = toDateTime.date;
        LocalTime toTime = toDateTime.time;

        // Smart date guesser for incomplete date formats

        if (toDate == null && fromDate != null) {
          toDate = fromDate;
        }
        if (toDate != null && fromDate == null) {
          fromDate = toDate;
        }
        if (fromTime == null && toTime != null) {
          fromTime = toTime;
        }
        if (fromTime != null && toTime == null) {
          toTime = fromTime;
        }
        return new EventCommand(description, doneness, fromDate, fromTime, toDate, toTime);

      } catch (Exception e) {
        throw new IncorrectFormatException();
      }
    } else {
      throw new IncorrectFormatException();
    }
  }

  public static Command fileLineParser(String input) throws DukeException {
    String[] parsedContent = input.split(" # ");
    boolean doneness = Integer.parseInt(parsedContent[1]) == 1;

    if (parsedContent.length == 3 && parsedContent[0].charAt(0) == 'T') {
      return todoParser(parsedContent[2], doneness);
    } else if (parsedContent.length == 4 && parsedContent[0].charAt(0) == 'D') {
      return deadlineParser(parsedContent[2] + " /by " + parsedContent[3], doneness);
    } else if (parsedContent.length == 5 && parsedContent[0].charAt(0) == 'E') {
      return eventParser(parsedContent[2] + " /from " + parsedContent[3] + " /to " + parsedContent[4], doneness);
    } else {
      throw new DukeException("Invalid file format");
    }
  }

  public static Command parse(String input) throws DukeException {
    String[] parsedInput = inputParser(input);
    String command = parsedInput[0].trim();
    String args = parsedInput[1].trim();

    try {
      DukeEnum commandtype = map(command);
      switch (commandtype) {
        case BYE:
          return new ByeCommand();
        case LIST:
          return new ListCommand();
        case MARK:
          return new MarkDonenessCommand(true, indexParser(args));
        case UNMARK:
          return new MarkDonenessCommand(false, indexParser(args));
        case TODO:
          return todoParser(args, false);
        case DEADLINE:
          return deadlineParser(args, false);
        case EVENT:
          return eventParser(args, false);
        case DELETE:
          return new DeleteCommand(indexParser(args));
        default:
          throw new InvalidCommandException();
      }
    } catch (DukeException e) {
      throw e;
    }
  }

  public static DukeEnum map(String command) throws DukeException {
    for (DukeEnum e : DukeEnum.values()) {
      if (command.equals(e.getText())) {
        return e;
      }
    }
    throw new InvalidCommandException();
  }

}
