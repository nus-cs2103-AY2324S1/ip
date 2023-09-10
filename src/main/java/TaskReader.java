import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class TaskReader {
    public static TaskList readTasksFromFile(String filename) {
        TaskList tasks = new TaskList();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTaskFromLine(line);
                if (task != null) {
                    tasks.addTaskFromStorage(task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    private static Task parseTaskFromLine(String line) {
        // Parse a task from a line of text
        if (line.startsWith("[T]")) {
            String description = line.substring(6).trim(); // Remove "[T][ ]" and leading spaces
            boolean isMarked = line.charAt(4) == 'X'; // Check if marked with 'X'
            return new Todo(description, isMarked);
        } else if (line.startsWith("[D]")) {
            // Parse Deadline task
            String description = line.substring(6, line.indexOf("(by:")).trim();
            boolean isMarked = line.charAt(4) == 'X';
            String dateSubstring = extractDeadline(line);
            String formattedDate = DateConverter.convertDate(dateSubstring);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate deadline = LocalDate.parse(formattedDate, formatter);
            return new Deadline(description, isMarked, deadline);

        } else if (line.startsWith("[E]")) {
            // Parse Event task
            String description = line.substring(6, line.indexOf("(from:")).trim();
            boolean isMarked = line.charAt(4) == 'X';
            String startTime = extractStartTime(line);
            String endTime = extractEndTime(line);
            return new Event(description, isMarked, startTime, endTime);
        }

        return null; // Return null for unsupported task formats
    }


    private static String extractDeadline(String line) {
        // Extract the deadline from the line
        int startIndex = line.indexOf("(by:") + 5;
        int endIndex = line.lastIndexOf(")");
        return line.substring(startIndex, endIndex).trim();
    }

    private static String extractStartTime(String line) {
        // Extract the start time from the line
        int startIndex = line.indexOf("(from:") + 7;
        int endIndex = line.indexOf("to:");
        return line.substring(startIndex, endIndex).trim();
    }

    private static String extractEndTime(String line) {
        // Extract the end time from the line
        int startIndex = line.indexOf("to:") + 4;
        int endIndex = line.lastIndexOf(")");
        return line.substring(startIndex, endIndex).trim();
    }
}





