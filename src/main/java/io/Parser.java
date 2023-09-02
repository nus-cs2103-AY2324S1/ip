package io;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import tasks.Deadline;
import tasks.Task;

public class Parser {

  public Parser() {
    inputTokens = null;
    inputString = null;
    scanner = new Scanner(System.in);
  }

  public void update() throws NoSuchElementException {
    inputString = scanner.nextLine();
    inputTokens = inputString.split(" ");
  }


  public String getInputString() {
    return this.inputString;
  }

  public String[] getInputTokens() {
    return this.inputTokens;
  }

  public int getIndex() throws IndexOutOfBoundsException {
    int index = Integer.parseInt(inputTokens[1]);
    index--;
    return index;
  }


  public Deadline parseDeadline() {
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
      System.out.println("Please include a (/by) command, followed by a date");
    } catch (StringIndexOutOfBoundsException ex) {
      System.out.println(
          "Please enter a name, followed by a (/by) command, followed by a date");
    } catch (DateTimeParseException ex) {
      System.out.println("Please enter a time format as dd/MM/yyyy");
    }

    return result;

  }


  public String getCommandString() {
    if (inputTokens.length == 0) {
      return "";
    } else {
      return inputTokens[0];
    }
  }

  public String getTaskName() {
    String commandString = this.getCommandString();
    int commandLength = commandString.length() + 1;
    return inputString.substring(commandLength);

  }

  public boolean isInputThere() {
    return inputTokens.length == 0;
  }


  private String inputString;
  private String[] inputTokens;


  private Scanner scanner;
}
