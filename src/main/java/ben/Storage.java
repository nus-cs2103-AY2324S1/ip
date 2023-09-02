package ben;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Represents the storage of previous tasks.
 */
public class Storage {
    /**
     * The file where the tasks are stored.
     */
    File f;

    /**
     * Constructor that takes in a file.
     *
     * @param f The file where the tasks are stored.
     */
    public Storage(File f) {
        this.f = f;
    }

    /**
     * Save tasks to the file by writing the file.
     *
     * @param tasks The task list.
     * @throws IOException Exception.
     */
    public void saveTasks(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(f);
        writer.write(tasks.saveTasks());
        writer.close();
    }

    /**
     * Converts a String to a LocalDateTime object if is in the valid format. If HHmm is not included in the String,
     * the time 2359 will be appended. Throws DateTImeParseException if the format is invalid.
     *
     * @param dateTime String representation of a LocalDateTime.
     * @return LocalDateTime object.
     * @throws DateTimeParseException Error for when DateTime cannot be parsed.
     */
    public LocalDateTime dateTimeParser(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        if (dateTime.length() <= 10) {
            return LocalDateTime.parse(dateTime + " 2359", formatter);
        }
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Loads tasks from the file and initialises the task list with tasks from the file.
     *
     * @param tasks The task list.
     * @throws FileNotFoundException Exception.
     */
    public void loadTasks(TaskList tasks) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] words = line.split("[|]");
            String command = words[0];

            switch (command.toLowerCase()) {
                case "t":
                    tasks.add(new ToDos(words[2], Boolean.parseBoolean(words[1])), false);
                    break;
                case "d":
                    tasks.add(new Deadlines(words[2],
                            Boolean.parseBoolean(words[1]), dateTimeParser(words[3])), false);
                    break;
                case "e":
                    tasks.add(new Events(words[2],
                                    Boolean.parseBoolean(words[1]), dateTimeParser(words[3]), dateTimeParser(words[4])),
                            false);
                    break;
            }
        }
    }
}
