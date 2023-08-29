import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private static String filePath = "tasks.txt";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static LocalDateTime saveAsDate(String by) throws DateTimeParseException {
        try {
            // Try to parse the input as "yyyy-MM-dd" format
            return LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            by += " 0000";
            // If parsing as "yyyy-MM-dd" format fails, try to parse as "yyyy-MM-dd HHmm" format
            return LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }
    }

    public static void generateTaskFileContent(ArrayList<Task> taskArray) throws IOException {
        StringBuilder content = new StringBuilder();
        for (Task task : taskArray) {
            content.append(writeTaskLine(task));
        }
        writeToFile("tasks.txt", content.toString());
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static String writeTaskLine(Task task) {
        Boolean isDone = task.getStatus();
        String taskLine = task.getType() + " | " + (isDone ? "1" : "0") + " | " + task.getDescription() + "\n";
        return taskLine;
    }


    public static ArrayList<Task> load() throws IOException, FileUnloadableException {
        ArrayList<Task> taskArray = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTaskFromString(line);
                taskArray.add(task);
            }
        }
        return taskArray;
    }

    private static Task parseTaskFromString(String line) {
        String[] parts = line.split("\\|");
        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String taskDescription = parts[2].trim();

        if (taskType.equals("T")) {
            Task task = new ToDo(taskDescription);
            setStatus(task, isDone);
            return task;
        } else if (taskType.equals("D")) {
            LocalDateTime by = Storage.saveAsDate(parts[3].trim());
            Task task = new Deadline(taskDescription, by);
            setStatus(task, isDone);
            return task;
        } else if (taskType.equals("E")) {
            LocalDateTime start = Storage.saveAsDate(parts[3].trim());
            LocalDateTime end = Storage.saveAsDate(parts[4].trim());
            Task task = new Event(taskDescription, start, end);
            setStatus(task, isDone);
            return task;
        } else {
            // Handle unrecognized task type
            return null;
        }
    }

    private static void setStatus(Task task, boolean isDone) {
        if (isDone) {
            task.markDone();
        } else {
            task.unmarkDone();
        }
    }
}
