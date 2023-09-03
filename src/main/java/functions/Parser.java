package functions;

import commands.*;
import tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public Parser() {}

    enum DateTimeFormat {
        FORMAT1("yyyy-MM-dd HH:mm"),
        FORMAT2("dd.MM.yyyy HH:mm"),
        FORMAT3("MM/dd/yyyy HH:mm"),
        FORMAT4("yyyy-MM-dd hh:mm a"),
        FORMAT5("dd.MM.yyyy hh:mm a"),
        FORMAT6("MM/dd/yyyy hh:mm a"),
        FORMAT7("yyyy-MM-dd HHmm"),
        FORMAT8("dd.MM.yyyy HHmm"),
        FORMAT9("MM/dd/yyyy HHmm");

        private final DateTimeFormatter formatter;

        DateTimeFormat (String pattern) {
            this.formatter = DateTimeFormatter.ofPattern(pattern);
        }

        public DateTimeFormatter getFormatter() {
            return formatter;
        }
    }

    public static LocalDateTime parseDate(String dateString) {
        for (DateTimeFormat format : DateTimeFormat.values()) {
            try {
                LocalDateTime d = LocalDateTime.parse(dateString, format.getFormatter());
                return d;
            } catch (DateTimeParseException e) {
                // Try the next format
            }
        }
        // None of the formats matched
        return null;
    }

    public static Command parse(String input) {
        if (input.equals("bye")) {
            return new ExitCommand();
        }
        if (input.equals("list")) {
            return new ListCommand();
        } else {
            String[] words = input.split(" ");
            String first = words[0];
            if (first.equals("delete")) {
                int s = Integer.valueOf(words[1]);
                return new DeleteCommand(s);
            } else if (first.equals("mark")) {
                int s = Integer.valueOf(words[1]);
                return new MarkCommand(s);
            } else if (first.equals("unmark")) {
                int s = Integer.valueOf(words[1]);
                return new UnmarkCommand(s);
            } else if (first.equals("find")) {
                String search = words[1];
                return new SearchCommand(search);
            } else if (first.equals("todo")) {
                if (input.length() <= 5) {
                    return new ErrorCommand("The description of a task cannot be empty.\n");
                } else {
                    String desc = input.substring(5);
                    return new AddCommand(desc);
                }
            } else if (first.equals("deadline")) {
                if (input.length() <= 9) {
                    return new ErrorCommand("The description of a task cannot be empty.\n");
                } else {
                    int y = input.indexOf("/by ");
                    Task t = null;
                    if (y == -1) {
                        return new ErrorCommand("Please enter the deadline of the task in the correct format.\n");
                    } else {
                        String desc = input.substring(9, y - 1);
                        String d = input.substring(y + 4);
                        LocalDateTime date = parseDate(d);
                        if (date == null) {
                            return new ErrorCommand("PLease enter the date and time in the correct format. \n");
                        }
                        return new AddCommand(desc, date);
                    }
                }
            } else if (first.equals("event")) {
                if (input.length() <= 6) {
                    return new ErrorCommand("The description of a task cannot be empty.\n");
                } else {
                    int fromIndex = input.indexOf("/from ");
                    int toIndex = input.indexOf("/to ");
                    if (fromIndex == -1 || toIndex == -1) {
                        return new ErrorCommand("Please enter the start and end time" +
                                " of the task in the correct format.\n");
                    } else {
                        String desc = input.substring(6, input.indexOf("/") - 1);
                        String s = input.substring(fromIndex + 6, toIndex).trim();
                        String e = input.substring(toIndex + 4).trim();
                        LocalDateTime start = parseDate(s);
                        LocalDateTime end = parseDate(e);
                        if (start == null || end == null) {
                            return new ErrorCommand("PLease enter the date and time in the correct format. \n");
                        }
                        return new AddCommand(desc, start, end);
                    }
                }
            } else {
                return new ErrorCommand("I'm sorry, but I don't know what that means :(\n");
            }
        }
    }
}
