package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * The Storage class handles loading and saving tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return An ArrayList containing the loaded tasks.
     * @throws DukeException If there is an error while loading tasks.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> dukeList = new ArrayList<>();
        String[] splitFilePath = this.filePath.split("/");
        String foldersPath = String.join("/", Arrays.copyOf(splitFilePath, splitFilePath.length - 1));
        new File(foldersPath).mkdirs();
        boolean fileExists;
        try {
            fileExists = !new File(filePath).createNewFile();
        } catch (IOException e) {
            throw new DukeException("Error creating file.");
        }
        if (fileExists) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(this.filePath));
            } catch (FileNotFoundException e) {
                throw new DukeException("File not found.");
            }
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("T")) {
                        String[] inputs = line.split(" \\| ", 3);
                        boolean isDone = inputs[1].equals("1");
                        ToDo todo = new ToDo(inputs[2], isDone);
                        dukeList.add(todo);
                    }
                    if (line.startsWith("D")) {
                        String[] inputs = line.split(" \\| ", 4);
                        boolean isDone = inputs[1].equals("1");
                        LocalDate b;
                        try {
                            b = DateParserService.parseDate(inputs[3]);
                        } catch (DateTimeParseException e) {
                            throw new DukeException("Invalid date format");
                        }
                        Deadline deadline = new Deadline(inputs[2], isDone, b);
                        dukeList.add(deadline);
                    }
                    if (line.startsWith("E")) {
                        String[] inputs = line.split(" \\| ", 5);
                        boolean isDone = inputs[1].equals("1");
                        LocalDate f;
                        LocalDate t;
                        try {
                            f = DateParserService.parseDate(inputs[3]);
                            t = DateParserService.parseDate(inputs[4]);
                        } catch (DateTimeParseException e) {
                            throw new DukeException("Invalid date format");
                        }
                        Event event = new Event(inputs[2], isDone, f, t);
                        dukeList.add(event);
                    }
                }
            } catch (IOException e) {
                throw new DukeException("Error reading file.");
            }
        }
        return dukeList;
    }

    /**
     * Writes tasks from the given DukeList to the file.
     *
     * @param data The DukeList containing tasks to be written to the file.
     * @throws IOException If there is an error while writing to the file.
     */
    public void writeToFile(DukeList data) throws IOException {
        FileWriter writer = new FileWriter(this.filePath);
        for (int i = 0; i < data.getSize(); i++) {
            Task entry = data.get(i);
            writer.write(entry.writeFile() + "\n");
        }
        writer.close();
    }
}
