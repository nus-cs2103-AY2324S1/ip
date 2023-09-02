import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Parser {

    public static String[] parseUserInput(String userInput) {
        return userInput.split("\\s+");
    }

    public static String getCommand(String[] tokens) {
        if (tokens.length > 0) {
            return tokens[0].toLowerCase();
        }
        return "";
    }
    public static String getToDoDescription(String[] tokens) throws IllegalArgumentDukeException {
        if (tokens.length < 2) {
            throw new IllegalArgumentDukeException("Oh no! You forgot to specify the task!");
        }
        return String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length));
    }

    public static String[] getDeadlineDescription(String[] tokens) throws IllegalArgumentDukeException {
        if (tokens.length < 2) {
            throw new IllegalArgumentDukeException("Oh no! You forgot to specify the task!");
        }

        boolean byFound = false;
        for (String token : tokens) {
            if (token.equals("/by")) {
                byFound = true;
                break;
            }
        }

        if (!byFound) {
            throw new IllegalArgumentDukeException("Whoopsie! The deadline seems a bit confused. Please use '/by' to set it.");
        }

        int index = -1;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].charAt(0) == '/') {
                index = i;
                tokens[i] = tokens[i].substring(1) + ":";
                break;
            }
        }
        String description = String.join(" ", Arrays.copyOfRange(tokens, 1, index));
        String by = String.join(" ", Arrays.copyOfRange(tokens, index + 1, tokens.length));

        String[] descriptions = {description, by};
        return descriptions;
    }

    public static String[] getEventDescription(String[] tokens) throws IllegalArgumentDukeException {
        if (tokens.length < 2) {
            throw new IllegalArgumentDukeException("Oh no! You forgot to specify the event!");
        }

        boolean foundFrom = false;
        boolean foundTo = false;

        for (String token : tokens) {
            if (token.equals("/from")) {
                foundFrom = true;
            } else if (foundFrom && token.equals("/to")) {
                foundTo = true;
                break;
            }
        }

        if (!(foundFrom && foundTo)) {
            throw new IllegalArgumentDukeException("Whoopsie! The deadline seems a bit confused. Please use '/from' and '/to' to set it.");
        }

        int indexStart = -1;
        int indexEnd = -1;
        boolean first = true;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].charAt(0) == '/') {
                if (first) {

                    indexStart = i;
                    first = false;
                    tokens[i] = tokens[i].substring(1) + ":";
                } else {
                    indexEnd = i;
                    tokens[i] = tokens[i].substring(1) + ":";
                    break;
                }
            }
        }

        String description = String.join(" ", Arrays.copyOfRange(tokens, 1, indexStart));
        String from = String.join(" ", Arrays.copyOfRange(tokens, indexStart, indexEnd));
        String to = String.join(" ", Arrays.copyOfRange(tokens, indexEnd, tokens.length));

        if (from.substring(5).trim().isEmpty() || to.substring(3).trim().isEmpty()) {
            throw new IllegalArgumentDukeException("Oh no! The start and/or end time cannot be empty.");
        }

        String[] descriptions = {description, from, to};
        return descriptions;
    }


    public static LocalDate parseDate(String dateStr) throws IllegalArgumentDukeException {
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentDukeException("Whoopsie! The deadline seems a bit confused. Please use a 'YYYY-MM-DD' format to set it.");
        }
    }

    public static int getTaskIndex(String[] tokens) {
        if (tokens.length > 1) {
            try {
                return Integer.parseInt(tokens[1]) - 1;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
}

