package com.alpha.utils;

import com.alpha.exceptions.InvalidTaskException.InvalidCommandException;
import com.alpha.exceptions.InvalidTaskException.InvalidDeadlineException;
import com.alpha.exceptions.InvalidTaskException.InvalidEventException;
import com.alpha.exceptions.InvalidTaskException.InvalidToDoException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

  private Parser() {
  }

  public static String[] getTokens(String text) throws InvalidCommandException {
    String[] tokens = text.split(" ");
    if (tokens.length == 0) {
      throw new InvalidCommandException();
    }
    return tokens;
  }

  public static String getTask(String text) {
    return text.split(" ")[0];
  }

  public static String getToDoName(String text) throws InvalidToDoException {
    String result = text.replaceFirst("todo", "").replaceFirst(" ", "");
    if (result.isEmpty()) {
      throw new InvalidToDoException();
    }
    return result;
  }

  public static String getDeadlineName(String text) throws InvalidDeadlineException {
    String result = text.replaceFirst("deadline", "").replaceFirst(" ", "");
    int indexOfLastBy = result.lastIndexOf("/by ");
    if (indexOfLastBy == -1) {
      throw new InvalidDeadlineException();
    }
    return result.substring(0, indexOfLastBy - 1);
  }

  public static String getDeadlineEnd(String text) throws InvalidDeadlineException {
    String result = text.replaceFirst("deadline", "").replaceFirst(" ", "");
    int indexOfLastBy = result.lastIndexOf("/by ");
    if (indexOfLastBy == -1 || indexOfLastBy + 4 >= result.length()) {
      throw new InvalidDeadlineException();
    }
    return result.substring(indexOfLastBy + 4);
  }

  public static String getEventName(String text) throws InvalidEventException {
    String result = text.replaceFirst("event", "").replaceFirst(" ", "");
    int indexOfLastFrom = result.lastIndexOf("/from");
    if (indexOfLastFrom == -1) {
      System.out.println("a");
      throw new InvalidEventException();
    }
    return result.substring(0, indexOfLastFrom);
  }

  public static String getEventStart(String text) throws InvalidEventException {
    String result = text.replaceFirst("event", "").replaceFirst(" ", "");
    int indexOfLastFrom = result.lastIndexOf("/from");
    int indexOfLastTo = result.lastIndexOf("/to");
    if (indexOfLastFrom == -1 || indexOfLastTo == -1 || indexOfLastFrom + 6 >= indexOfLastTo) {
      System.out.println("b");
      throw new InvalidEventException();
    }
    return result.substring(indexOfLastFrom + 6, indexOfLastTo - 1);
  }

  public static String getEventEnd(String text) throws InvalidEventException {
    String result = text.replaceFirst("event", "").replaceFirst(" ", "");
    int indexOfLastTo = result.lastIndexOf("/to ");
    if (indexOfLastTo == -1 || indexOfLastTo + 4 >= result.length()) {
      System.out.println(result.length());
      throw new InvalidEventException();
    }
    return result.substring(indexOfLastTo + 4);
  }

  public static LocalDateTime parseDateTimeString(String text) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return LocalDateTime.parse(text, formatter);
  }

  public static String parseDateTimeObjectToDisplay(LocalDateTime localDateTime) {
    return localDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
  }

  public static String parseDateTimeObjectToStore(LocalDateTime localDateTime) {
    return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
  }
}
