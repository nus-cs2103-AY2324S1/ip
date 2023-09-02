import jdk.jshell.spi.SPIResolutionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads into TASKS saved tasks from a file.
     * @throws IOException, ParseException
     */
    public ArrayList<Task> loadIntoTasks() throws IOException, ParseException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                // parse the line
                Task task = parseSavedTask(line);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            // make the file
            this.file.createNewFile();
            return new ArrayList<>();
        }
        return tasks;
    }

    /**
     * Parses a single task saved in the text file.
     * @param taskString Line to be parsed.
     * @return A new Task.
     */
    private Task parseSavedTask(String taskString) throws ParseException {
        String[] components = taskString.split("\\|");
        Task task;
        switch (components[0]) {
            case "E":
                task = new Event(
                        components[1],
                        DateUtils.parseDateTime(components[3]),
                        DateUtils.parseDateTime(components[4]));
                break;
            case "T":
                task = new Todo(components[1]);
                break;
            case "D":
                task = new Deadline(
                        components[1],
                        DateUtils.parseDateTime(components[3]));
                break;
            default:
                throw new IllegalArgumentException();
        }
        if (components[2].equals("X")) {
            task.mark();
        }
        return task;
    }

    /**
     * Saves the Tasks in TASKS to a given file.
     *
     * @param tasks The required tasks to parse
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (PrintWriter fileWriter = new PrintWriter(this.file)) {
            for (Task task : tasks) {
                fileWriter.println(task.toFormat());
            }
        } catch (IOException e) {
            // handle error
        }
    }
}
