package io;

import exceptions.ParserException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;

/**
 * The Parser class handles user input from the command line.
 */
public class Parser {

    public Parser() {
        inputTokens = null;
        inputString = null;
        scanner = new Scanner(System.in);
    }

  /**
   * Waits for new input from the command line and updates internal variables.
   *
   * @throws NoSuchElementException If no further input is available.
   */
  public void update() throws NoSuchElementException {
    inputString = scanner.nextLine();
    inputTokens = inputString.split(" ");
  }

  /**
   * Returns the currently stored input string.
   *
   * @return The stored input string.
   */
  public String getInputString() {
    return this.inputString;
  }

  /**
   * Returns the current input tokens stored in the parser.
   *
   * @return An array of input tokens.
   */
  public String[] getInputTokens() {
    return this.inputTokens;
  }

  /**
   * Returns an integer that the user has input after a command.
   *
   * @return The integer input by the user.
   * @throws IndexOutOfBoundsException If the index is not within bounds.
   */
  public int getIndex() throws IndexOutOfBoundsException {
    int index = Integer.parseInt(inputTokens[1]);
    index--;
    return index;
  }


  /**
   * Parses the user's input and returns a Deadline task if the input is valid.
   *
   * @return A Deadline task parsed from the user's input.
   * @throws ParserException If the input is not valid for parsing.
   */
  public Deadline parseDeadline() throws ParserException {
    Deadline result = null;

        try {
            String taskName = this.getTaskName();
            String[] parts = taskName.split("/by", 2);

            String name = parts[0];
            String endDate = parts[1];
            endDate = endDate.replace(" ", "");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(endDate, formatter);

            result = new Deadline(name, date);

        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new ParserException("Please include a (/by) command, followed by a date");
        } catch (StringIndexOutOfBoundsException ex) {
            throw new ParserException(
                "Please enter a name, followed by a (/by) command, followed by a date");
        } catch (DateTimeParseException ex) {
            throw new ParserException("Please enter a time format as dd/MM/yyyy");
        }

        return result;

    }

  /**
   * Parses the user's input and returns an Event object if the input is valid.
   *
   * @return An Event object parsed from the user's input.
   * @throws ParserException If the input is not valid for parsing.
   */
  public Event parseEvent() throws ParserException {
    Event result = null;
    try {
      String taskName = this.getTaskName();
      String[] parts = taskName.split("/from", 2);
      String name = parts[0];
      String dates = parts[1];
      String[] datesplit = dates.split("/to", 2);
      String startDate = datesplit[0];
      String endDate = datesplit[1];

            result = new Event(name, startDate, endDate);
        } catch (StringIndexOutOfBoundsException ex) {
            throw new ParserException("The event command cannot be empty!");
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new ParserException(
                "Please enter a name, followed by a (/from) command, followed by a date, followed by a (/to) command and a date");
        }
        return result;
    }

  /**
   * Returns the user's input command.
   *
   * @return The input command as a string.
   */
  public String getCommandString() {
    if (inputTokens.length == 0) {
      return "";
    } else {
      return inputTokens[0];
    }
  }

  /**
   * Returns the task name that the user has input.
   *
   * @return The task name as a string.
   */
  public String getTaskName() {
    String commandString = this.getCommandString();
    int commandLength = commandString.length() + 1;
    return inputString.substring(commandLength);

    }

  /**
   * Checks if the user has entered any input.
   *
   * @return true if there is input, false otherwise.
   */
  public boolean isInputThere() {
    return inputTokens.length == 0;
  }


    private String inputString;
    private String[] inputTokens;


    private Scanner scanner;
}
