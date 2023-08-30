package Parser;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

public class DataParser {
    public static Task parseLineToTask(String line) {
        String typeIndicator = line.substring(0, 3);

        switch (typeIndicator) {
            case "[D]":
                return parseDeadline(line);
            case "[E]":
                return parseEvent(line);
            case "[T]":
                return parseToDo(line);
            default:
                return null;
        }
    }

    public static Deadline parseDeadline(String line) {
        int descriptionStart = "deadline".length() + 1; // Length of "deadline" plus the space
        int byIndex = line.indexOf("by:");

        String description = line.substring(descriptionStart, byIndex).trim();
        String date = line.substring(byIndex + "/by".length()).trim();

        boolean isMarked = line.charAt(4) == 'X'; // Assuming index 4 corresponds to the "[ ]" checkmark

        return new Deadline(description, date, isMarked);
    }

    public static Event parseEvent(String line) {
        int descriptionStart = line.indexOf("] ") + 2; // Index of the first character after "] "
        int startFromIndex = line.indexOf("from ") + 6; // Index of the character after "from: "
        int toIndex = line.indexOf("to ", startFromIndex); // Index of the character before "to: "
        int endFromIndex = toIndex + 4; // Index of the character after "to: "

        String description = line.substring(descriptionStart, startFromIndex - 8).trim(); // Subtracting 8 to exclude "(from: "
        String start = line.substring(startFromIndex, toIndex).trim();
        String end = line.substring(endFromIndex).trim();

        boolean isMarked = line.charAt(4) == 'X'; // Assuming index 4 corresponds to the "[ ]" checkmark

        return new Event(description, start, end, isMarked);
    }

    public static ToDo parseToDo(String line) {
        int descriptionStart = line.indexOf("] ") + 2; // Index of the first character after "] "
        String description = line.substring(descriptionStart).trim();

        boolean isMarked = line.charAt(4) == 'X'; // Assuming index 4 corresponds to the "[ ]" checkmark

        return new ToDo(description, isMarked);
    }
}
