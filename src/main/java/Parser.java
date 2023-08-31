import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    static String descr;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy HHmm");
    static LocalDateTime dateTime = LocalDateTime.now();
    static LocalDateTime dateTime2 = LocalDateTime.now();

    public static Command parseList(String[] words) {
        return new ListCommand();
    }

    public static Task parseTodo(String[] words) throws MissingDescriptionException {
        descr = "";
        if (words.length < 2) {
            throw new MissingDescriptionException("todo");
        }
        for (int i = 1; i < words.length; i++) {
            descr = descr + words[i] + " ";
        }
        descr = descr.substring(0, descr.length() - 1);
        return new Todo(descr);
    }

    public static Task parseDeadline(String[] words) throws MissingDescriptionException, MissingTimeException, InvalidDateTimeException {
        boolean by = false;
        descr = "";
        String by_text = "";
        for (int i = 1; i < words.length; i++) {
            if (words[i].equals("/by")) {
                by = true;
                continue;
            }
            if (by) {
                by_text = by_text + words[i] + " ";
            } else {
                descr = descr + words[i] + " ";
            }
        }
        if (descr.equals("")) {
            throw new MissingDescriptionException("deadline");
        } else if (by_text.equals("")) {
            throw new MissingTimeException("ending", "deadline");
        }
        descr = descr.substring(0, descr.length() - 1);
        by_text = by_text.substring(0, by_text.length() - 1);
        try {
            dateTime = LocalDateTime.parse(by_text, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
        return new Deadline(descr, dateTime);
    }

    public static Task parseEvent(String[] words) throws MissingDescriptionException, MissingTimeException, InvalidDateTimeException {
        boolean from = false;
        boolean to = false;
        descr = "";
        String from_text = "";
        String to_text = "";
        for (int i = 1; i < words.length; i++) {
            if (words[i].equals("/from")) {
                from = true;
                continue;
            } else if (words[i].equals("/to")){
                to = true;
                continue;
            }
            if (to) {
                to_text = to_text + words[i] + " ";
            } else if (from) {
                from_text = from_text + words[i] + " ";
            } else {
                descr = descr + words[i] + " ";
            }
        }
        if (descr.equals("")) {
            throw new MissingDescriptionException("event");
        } else if (from_text.equals("")) {
            throw new MissingTimeException("start", "event");
        } else if (to_text.equals("")) {
            throw new MissingTimeException("end", "event");
        }
        descr = descr.substring(0, descr.length() - 1);
        from_text = from_text.substring(0, from_text.length() - 1);
        to_text = to_text.substring(0, to_text.length() - 1);
        try {
            dateTime = LocalDateTime.parse(from_text, formatter);
            dateTime2 = LocalDateTime.parse(to_text, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }

        return new Event(descr, dateTime, dateTime2);
    }

    public static Command parseMark(String[] words) throws InvalidNumberException, InvalidRangeException {
        if (words.length < 2) {
            throw new InvalidNumberException("mark");
        }
        try {
            return new MarkCommand(Integer.parseInt(words[1])-1);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException("mark");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidRangeException();
        }
    }

    public static Command parseUnmark(String[] words) throws InvalidNumberException, InvalidRangeException {
        if (words.length < 2) {
            throw new InvalidNumberException("mark");
        }
        try {
            return new MarkCommand(Integer.parseInt(words[1])-1);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException("mark");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidRangeException();
        }
    }

    public static Command parseDelete(String[] words) throws InvalidNumberException, InvalidRangeException {
        if (words.length < 2) {
            throw new InvalidNumberException("delete");
        }
        try {
            return new DeleteCommand(Integer.parseInt(words[1])-1);
        } catch (NumberFormatException ex) {
            throw new InvalidNumberException("delete");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidRangeException();
        }
    }

    public static Command parse(String input) throws MissingDescriptionException, MissingTimeException, InvalidDateTimeException, InvalidNumberException, InvalidRangeException {

        String[] words = input.split(" ");

        switch (words[0]) {
                case "list":
                    return parseList(words);

                case "todo":
                    return new AddCommand(parseTodo(words));

                case "deadline":
                    return new AddCommand(parseDeadline(words));

                case "event":
                    return new AddCommand(parseEvent(words));

                case "mark":
                    return parseMark(words);

                case "unmark":
                    return parseUnmark(words);

                case "delete":
                    return parseDelete(words);

                case "bye":
                    return new ExitCommand();

                default:
                    return new InvalidCommand();
            }
    }

    public static Task parseLoading(String input) throws MissingDescriptionException, MissingTimeException, InvalidDateTimeException, InvalidNumberException, InvalidRangeException {

        String[] words = input.split(" ");

        switch (words[0]) {

            case "todo":
                return parseTodo(words);

            case "deadline":
                return parseDeadline(words);

            case "event":
                return parseEvent(words);

            default:
                return new Task("");

        }
    }


}
