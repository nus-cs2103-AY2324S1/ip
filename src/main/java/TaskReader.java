import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TaskReader {

    public static ArrayList<Task> readTasksFromFile(String fileName) {
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = createTaskFromLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public static Task createTaskFromLine(String line) {
        String typeIndicator = line.substring(0, 3);

        if (typeIndicator.equals("[D]")) {
            return createDeadlineFromLine(line);
        } else if (typeIndicator.equals("[E]")) {
            return createEventFromLine(line);
        } else if (typeIndicator.equals("[T]")) {
            return createToDoFromLine(line);
        } else {
            return null; // Invalid task type
        }
    }

    public static Deadline createDeadlineFromLine(String line) {
        int descriptionStart = line.indexOf("] ") + 2; // Index of the first character after "] "
        int dateStart = line.indexOf("(", descriptionStart) + 1; // Index of the character after "("
        int dateEnd = line.indexOf(")", dateStart); // Index of the character before ")"

        String description = line.substring(descriptionStart, dateStart - 1).trim();
        String date = line.substring(dateStart, dateEnd).trim();

        boolean isMarked = line.charAt(4) == 'X'; // Assuming index 4 corresponds to the "[ ]" checkmark

        return new Deadline(description, date, isMarked);
    }

    public static Event createEventFromLine(String line) {
        int descriptionStart = line.indexOf("] ") + 2; // Index of the first character after "] "
        int startFromIndex = line.indexOf("from: ") + 6; // Index of the character after "from: "
        int toIndex = line.indexOf("to: ", startFromIndex); // Index of the character before "to: "
        int endFromIndex = toIndex + 4; // Index of the character after "to: "
        int endEndIndex = line.indexOf(")", endFromIndex); // Index of the character before ")"

        String description = line.substring(descriptionStart, startFromIndex - 8).trim(); // Subtracting 8 to exclude "(from: "
        String start = line.substring(startFromIndex, toIndex).trim();
        String end = line.substring(endFromIndex, endEndIndex).trim();

        boolean isMarked = line.charAt(4) == 'X'; // Assuming index 4 corresponds to the "[ ]" checkmark

        return new Event(description, start, end, isMarked);
    }

    public static ToDo createToDoFromLine(String line) {
        int descriptionStart = line.indexOf("] ") + 2; // Index of the first character after "] "
        String description = line.substring(descriptionStart).trim();

        boolean isMarked = line.charAt(4) == 'X'; // Assuming index 4 corresponds to the "[ ]" checkmark

        return new ToDo(description, isMarked);
    }
}
