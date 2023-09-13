package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Storage {
    private static String filePath;
    public Storage(String filePath){
        Storage.filePath = filePath;
    }
    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(Storage.filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
    public static void preloadFromFile(TaskList list) {
        try {
            File f = new File("src/main/java/duke/data/duke.txt");
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNext()) {
                String text = fileScanner.nextLine();
                if (text.startsWith("unmark")) {
                    String[] splitText = text.split(" ");
                    int numToUnmark = Integer.parseInt(splitText[1]) - 1;
                    list.get(numToUnmark).markAsIncomplete();
                    continue;
                } else if (text.startsWith("mark")) {
                    String[] splitText = text.split(" ");
                    int numToMark = Integer.parseInt(splitText[1]) - 1;
                    list.get(numToMark).markAsComplete();
                    continue;
                } else if (text.startsWith("todo")) {
                    String description = text.substring(4);
                    if (description.isEmpty()) {
                        continue;
                    } else {
                        Todo todo = new Todo(description.trim());
                        list.add(todo);
                    }
                } else if (text.startsWith("deadline")) {
                    String[] splitText = text.split("/", 2);
                    String description = splitText[0].substring(8);
                    String deadlineText = splitText[1].substring(3);
                    LocalDateTime deadline = LocalDateTime.parse(deadlineText);
                    Deadline dl = new Deadline(description.trim(), deadline);
                    list.add(dl);

                } else if (text.startsWith("event")) {
                    String[] splitText = text.split("/", 3);
                    String description = splitText[0].substring(5);
                    String startText = splitText[1].trim().substring(5);
                    String endText = splitText[2].trim().substring(3);

                    LocalDateTime start = LocalDateTime.parse(startText);
                    LocalDateTime end = LocalDateTime.parse(endText);
                    Event event = new Event(description.trim(), start, end);
                    list.add(event);
                } else if (text.startsWith("delete")) {
                    String[] splitText = text.split(" ");
                    int numToDelete = Integer.parseInt(splitText[1]) - 1;
                    list.remove(numToDelete);
                } else if (text.startsWith("sort")) {
                    list.sort(new TaskTagComparator());
                    list.sort(new TaskDateComparator());
                }
                else {
                    continue;
                }
            }
        } catch(FileNotFoundException e){
            System.out.println("File not found!");
        }
    }
}
