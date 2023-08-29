import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private String filePath;

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




}
