package minion.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import minion.data.task.Deadline;
import minion.data.task.Event;
import minion.data.task.Task;
import minion.data.task.ToDo;

/**
 * Represents a File parser.
 */
public class FileParser {
    private static final String FILE_DELIMITER = " \\| ";

    /**
     * Parses a file into a list of tasks.
     * @param file The file to be parsed.
     * @return The list of tasks.
     */
    public static List<Task> parse(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        List<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String[] arr = str.split(FILE_DELIMITER);
            boolean isDone = arr[1].equals("1");
            String description = arr[2];

            switch (arr[0]) {
            case "T":
                tasks.add(new ToDo(description, isDone));
                break;

            case "D":
                tasks.add(new Deadline(description, isDone, arr[3]));
                break;

            case "E":
                String[] tmp = arr[3].split(" - ");
                String from = tmp[0];
                String to = tmp[1];
                tasks.add(new Event(description, isDone, from, to));
                break;

            default:
                break;
            }
        }
        sc.close();
        return tasks;
    }
}
