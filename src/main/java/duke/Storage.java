package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.parser.DateParser;

/** Stores the task list on to the hard drive */
public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads data.txt file and stores the task into a list.
     * If data.txt file does not exist, it will attempt to create a data.txt file
     *
     * @return The list retrieve from the file data.txt.
     */
    public List<Task> load() {
        List<Task> list = new ArrayList<>();
        try {
            File file = new File(filePath);
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
                default:
                    throw new IllegalArgumentException("OOPS! Something went wrong");
                }
            }
            fileReader.close();
            return list;
        } catch (FileNotFoundException error) {
            createFile(filePath);
        }
        return list;
    }

    /**
     * Creates a file named data.txt.
     *
     * @param filePath Path to the file data.txt.
     */
    public void createFile(String filePath) {
        try {
            File file = new File(filePath);
            file.createNewFile();
            load();
        } catch (IOException error) {
            throw new IllegalArgumentException("Oops! Something went wrong!");
        }
    }

    /**
     * Goes through all task stored in list and updates the hard drive.
     *
     * @param list List to update the data.
     */
    public static void updateStorage(String filePath, TaskList list) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < list.length(); i++) {
                String description = list.retrieve(i).getStorageDescription();
                writer.write(description + "\n");
            }
            writer.close();
        } catch (IOException error) {
            throw new IllegalArgumentException("Oops! Something went wrong!");
        }
    }
}
