package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Storage {
    private static String filePath;
    static private File directory;
    static private File file;
    public Storage(String filePath){
        Storage.filePath = filePath;
        directory = new File(filePath.substring(0, filePath.lastIndexOf("/")));
        file = new File(filePath);
    }
    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(Storage.filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
    public static void preloadFromFile(TaskList list) throws DukeException {
        // check if data directory exists

        try {
            if (!directory.exists()) {
                directory.mkdir();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
//            File f = new File("src/main/java/duke/data/duke.txt");
            File f = new File(Storage.filePath);
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNext()) {
                String text = fileScanner.nextLine();
                if (text.startsWith("unmark")) {
                    unmark(list, text);

                } else if (text.startsWith("mark")) {
                    mark(list, text);

                } else if (text.startsWith("todo")) {
                    createTodo(list, text);
                } else if (text.startsWith("deadline")) {
                    createDeadline(list, text);

                } else if (text.startsWith("event")) {
                    createEvent(list, text);
                } else if (text.startsWith("delete")) {
                    delete(list, text);
                } else if (text.startsWith("sort")) {
                    sort(list);
                } else {
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            // shouldn't happen
            System.out.println("File not found!");
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private static void sort(TaskList list) {
        list.sort(new TaskTagComparator());
        list.sort(new TaskDateComparator());
    }

    private static void delete(TaskList list, String text) {
        String[] splitText = text.split(" ");
        int numToDelete = Integer.parseInt(splitText[1]) - 1;
        list.remove(numToDelete);
    }

    private static void createEvent(TaskList list, String text) {
        String[] splitText = text.split("/", 3);
        String description = splitText[0].substring(5);
        String startText = splitText[1].trim().substring(5);
        String endText = splitText[2].trim().substring(3);

        LocalDateTime start = LocalDateTime.parse(startText);
        LocalDateTime end = LocalDateTime.parse(endText);
        Event event = new Event(description.trim(), start, end);
        list.add(event);
    }

    private static void createDeadline(TaskList list, String text) {
        String[] splitText = text.split("/", 2);
        String description = splitText[0].substring(8);
        String deadlineText = splitText[1].substring(3);
        LocalDateTime deadline = LocalDateTime.parse(deadlineText);
        Deadline dl = new Deadline(description.trim(), deadline);
        list.add(dl);
    }

    private static void createTodo(TaskList list, String text) {
        String description = text.substring(4);
        if (description.isEmpty()) {
            return;
        } else {
            Todo todo = new Todo(description.trim());
            list.add(todo);
        }
    }

    private static void mark(TaskList list, String text) {
        String[] splitText = text.split(" ");
        int numToMark = Integer.parseInt(splitText[1]) - 1;
        list.get(numToMark).markAsComplete();
    }

    private static void unmark(TaskList list, String text) {
        String[] splitText = text.split(" ");
        int numToUnmark = Integer.parseInt(splitText[1]) - 1;
        list.get(numToUnmark).markAsIncomplete();
    }
}
