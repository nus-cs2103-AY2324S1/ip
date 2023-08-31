package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Stores the task list on to the hard drive */
public class Storage {

    private final String FILE_PATH;

    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    /** Reads data.txt file and stores the task into a list.
     *  If data.txt file does not exist, it will attempt to create a data.txt file
     *
     * @return The list retrieve from the file data.txt.
     */
    public List<Task> load() {
        List<Task> list = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String task = fileReader.nextLine();
                String[] taskDescription = task.split(" ", 3);
                String taskType = taskDescription[0];
                boolean isDone = taskDescription[1].equals("1");
                String description = taskDescription[2];
                switch (taskType) {
                case "T":
                    Task toDoTask = new ToDo(description, isDone);
                    list.add(toDoTask);
                    break;
                case "D":
                    String[] splitDeadline = description.split("/by", 2);
                    String deadlineTaskDescription = splitDeadline[0];
                    LocalDate deadline = DateParser.parseDate(splitDeadline[1]);
                    Task deadlineTask = new Deadline(deadlineTaskDescription, isDone, deadline);
                    list.add(deadlineTask);
                    break;
                case "E":
                    String[] splitEvent = description.split("/from", 2);
                    String eventTaskDescription = splitEvent[0];
                    String[] timings = splitEvent[1].split("/to", 2);
                    LocalDate start = DateParser.parseDate(timings[0]);
                    LocalDate end = DateParser.parseDate(timings[1]);
                    Task eventTask = new Event(eventTaskDescription, isDone, start, end);
                    list.add(eventTask);
                    break;
                }
            }
            fileReader.close();
            return list;
        } catch (FileNotFoundException error) {
            createFile(FILE_PATH);
        }
        return null;
    }

    /** Creates a file named data.txt. */
    public void createFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.createNewFile()) {
                load();
            }
        } catch (IOException error) {
           throw new IllegalArgumentException("Oops! Something went wrong!");
        }
    }
}
