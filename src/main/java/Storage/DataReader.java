package Storage;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {

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
            System.out.println("Error reading the file or the file doesn't exist.");
            System.out.println("Please try again after ensuring the correctness of the file.");
            System.exit(1);
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
            return null;
        }
    }

    public static Deadline createDeadlineFromLine(String line) {
        int descriptionStart = "deadline".length() + 1; // Length of "deadline" plus the space
        int byIndex = line.indexOf("by:");

        String description = line.substring(descriptionStart, byIndex).trim();
        String date = line.substring(byIndex + "/by".length()).trim();

        boolean isMarked = line.charAt(4) == 'X'; // Assuming index 4 corresponds to the "[ ]" checkmark

        return new Deadline(description, date, isMarked);
    }

    public static Event createEventFromLine(String line) {
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


    public static ToDo createToDoFromLine(String line) {
        int descriptionStart = line.indexOf("] ") + 2; // Index of the first character after "] "
        String description = line.substring(descriptionStart).trim();

        boolean isMarked = line.charAt(4) == 'X'; // Assuming index 4 corresponds to the "[ ]" checkmark

        return new ToDo(description, isMarked);
    }
}
