import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.FileReader;

public class Load {

    TaskList taskList;
    private String loadFilePath;

    public Load(String loadFilePath) {
        this.loadFilePath = loadFilePath;
        this.taskList = new TaskList();
    }

    public TaskList load() throws IOException {
        FileReader f = null;

        try {
            f = new FileReader(loadFilePath);
        } catch (IOException e) {
            System.out.println("File does not exist in directory.");
            return null;
        }

        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String currentTaskAsString = s.nextLine();
            String taskType = currentTaskAsString.substring(1,2).toUpperCase();
            boolean isDone = currentTaskAsString.substring(4, 5).toUpperCase().equals("X");
            int descriptionBeginIndex = 7;
            Integer descriptionEndIndex = null;

            String description = null;

            switch (taskType) {
                case "T":
                    description = currentTaskAsString.substring(descriptionBeginIndex);
                    ToDo todo = new ToDo(description, isDone);
                    taskList.add(todo);
                    break;

                case "D":
                    descriptionEndIndex = currentTaskAsString.indexOf("(by:")-1;
                    int deadlineStartIndex = currentTaskAsString.indexOf("(by:") + 5;
                    description = currentTaskAsString.substring(descriptionBeginIndex, descriptionEndIndex);
                    String deadlineTimeString = currentTaskAsString.substring(deadlineStartIndex, currentTaskAsString.length()-1);
                    LocalDateTime deadlineTime = null;
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                        deadlineTime = LocalDateTime.parse(deadlineTimeString, formatter);
                    } catch (Exception e) {
                        System.out.println("Deadline " + description + " cannot be loaded.");
                        break;
                    }
                    Deadline deadline = new Deadline(description, deadlineTime, isDone);
                    taskList.add(deadline);
                    break;

                case "E":
                    descriptionEndIndex = currentTaskAsString.indexOf("(from:")-1;
                    int fromTimingStartIndex = currentTaskAsString.indexOf("(from:") + 7;
                    int fromTimingEndIndex = currentTaskAsString.indexOf("to:")-1;
                    int toTimingStartIndex = fromTimingEndIndex + 5;
                    int toTimingEndIndex = currentTaskAsString.length() - 1;
                    description = currentTaskAsString.substring(descriptionBeginIndex, descriptionEndIndex);
                    String fromString = currentTaskAsString.substring(fromTimingStartIndex, fromTimingEndIndex);
                    String toString = currentTaskAsString.substring(toTimingStartIndex, toTimingEndIndex);
                    LocalDateTime fromDateTime = null;
                    LocalDateTime toDateTime = null;
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                        fromDateTime = LocalDateTime.parse(fromString, formatter);
                        toDateTime = LocalDateTime.parse(toString, formatter);
                    } catch (Exception e) {
                        System.out.println("Event " + description + " cannot be loaded.");
                        break;
                    }

                    Event event = new Event(description, fromDateTime, toDateTime, isDone);
                    taskList.add(event);
                    break;
            }

        }
        f.close();
        return this.taskList;
    };
}
