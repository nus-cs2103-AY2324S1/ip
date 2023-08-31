package cheese.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.IllegalFormatException;

import cheese.Task.Task;
import cheese.TaskList.TaskList;

public class Parser {
    final static String byPatternString = "\\(by: (.*?)\\)";
    final static String fromPatternString = "from: (.*?) to:";
    final static String toPatternString = "to: (.*)\\)$";

    final static Pattern byPattern = Pattern.compile(byPatternString);
    final static Pattern fromPattern = Pattern.compile(fromPatternString);
    final static Pattern toPattern = Pattern.compile(toPatternString);

    public boolean isBy(String input) {
        Matcher byMatcher = byPattern.matcher(input);
        return byMatcher.find();
    }

    public Matcher matchBy(String input) {
        Matcher byMatcher = byPattern.matcher(input);

        byMatcher.find();
        return byMatcher;
    }



    public boolean isFrom(String input) {
        Matcher fromMatcher = fromPattern.matcher(input);
        return fromMatcher.find();
    }

    public Matcher matchFrom(String input) {
        Matcher fromMatcher = fromPattern.matcher(input);
        fromMatcher.find();
        return fromMatcher;
    }

    public boolean isTo(String input) {
        Matcher toMatcher = toPattern.matcher(input);
        return toMatcher.find();
    }

    public Matcher matchTo(String input) {
        Matcher toMatcher = toPattern.matcher(input);
        toMatcher.find();
        return toMatcher;
    }

    public LocalDate dateTimeConverted(String dateInput) {
        DateTimeFormatter inputformat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputformat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        Optional<LocalDate> localDate = parseDate(dateInput, inputformat);
        if (localDate.isPresent()) {
            return localDate.get();
        } else {
            LocalDate localDateMMM = parseMMMFormat(dateInput, outputformat);

            if (localDateMMM != null) {
                return localDateMMM;
            }
        }
        return null;
    }

    private LocalDate parseMMMFormat(String dateInput, DateTimeFormatter outputformat) {
        try {
            return LocalDate.parse(dateInput, outputformat);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private Optional<LocalDate> parseDate(String dateInput, DateTimeFormatter inputformat) {
        try {
            return Optional.of(LocalDate.parse(dateInput, inputformat));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    public boolean isCommand(String input) {
        String[] inputSplit = input.split(" ");
        switch (inputSplit[0]) {
            case "mark":
            case "bye":
            case "list":
            case "todo":
            case "deadline":
            case "event":
            case "delete":
                return true;
            default:
                return false;
        }
    }

    public String getCommand(String input) {
        String[] inputSplit = input.split(" ");
        return inputSplit[0];
    }

    public Task parseTask(String input) {
      String[] inputSplit = input.split(" ", 2);
      String command = inputSplit[0];
      Task newTask = null;
      try {
        if (inputSplit.length < 2 && isCommand(input)) {
          throw new IllegalArgumentException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
        } else if (inputSplit.length < 2 && !isCommand(input)) {
          throw new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        String taskDescription = inputSplit[1];
        switch (command) {
          case "todo":
            //System.out.println("Stuff1: " + taskDescription);
            newTask = new Task('T',taskDescription);
            break;
          case "deadline":
          String[] deadlineSplit = taskDescription.split(" /by ", 2);
          LocalDate deadlineDate = dateTimeConverted(deadlineSplit[1].trim());
          if (deadlineDate != null) {
            System.out.println("Its proper LocalDate");
            newTask = new Task('D',deadlineSplit[0].trim(), deadlineDate);
          } else {
            newTask = new Task('D',deadlineSplit[0].trim(), deadlineSplit[1].trim());
          }
          break;

          case "event":
          String[] eventInfo = taskDescription.split(" /from ", 2);
          String[] eventInfo2 = eventInfo[1].split(" /to ", 2);
          newTask = new Task('E',eventInfo[0].trim(), eventInfo2[0].trim(), eventInfo2[1].trim());
          break;

          default:
            throw new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    return newTask;
  }
}

