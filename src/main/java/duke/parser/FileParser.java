package duke.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.DeadlinesTask;
import duke.task.EventsTask;
import duke.task.Task;
import duke.task.TodoTask;

/**
 * Represents the FileParser.
 */
public class FileParser {
    private static final String SEPERATOR = ",";

    /**
     * Parses the file.
     * @param f File to be parsed.
     * @return ArrayList of Tasks.
     * @throws FileNotFoundException If file cannot be found.
     */
    public static ArrayList<Task> parse(File f) throws FileNotFoundException {
        Scanner sc = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] data = line.split(SEPERATOR);
            String type = data[0];
            String mark = data[1];
            String name = data[2];
            String by;
            String from;
            String to;
            boolean isDone = !mark.equals("0");
            switch (type) {
            case "T":
                tasks.add(new TodoTask(name, isDone));
                break;
            case "D":
                by = data[3];
                tasks.add(new DeadlinesTask(name, isDone, by));
                break;
            case "E":
                from = data[3];
                to = data[4];
                tasks.add(new EventsTask(name, isDone, from, to));
                break;
            default:
                break;
            }
        }
        sc.close();
        return tasks;
    }
}
