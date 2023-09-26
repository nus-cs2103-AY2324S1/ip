package duke;

import duke.exceptions.DukeInvalidFileException;
import duke.tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * The <code>Storage</code> object handles loading and saving from the computer.
 */

public class Storage {
    private File file;
    private File directory;
    private String filePath;
    private boolean hasDirectory = false;

    /**
     * The class constructor.
     *
     * @param filePath The file path to be written to and loaded from. May already have a file or not.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
        int lastSlash = filePath.lastIndexOf('/');
        if (lastSlash != -1) {
            String dirPath = filePath.substring(0, lastSlash);
            this.directory = new File(dirPath);
            this.hasDirectory = true;
        }
    }

    /**
     * Loads the current content of the file.
     * If the file does not exist, it creates one.
     *
     * @throws DukeInvalidFileException if the file creation fails.
     */
    public List<Task> load() throws DukeInvalidFileException {
        List<Task> contents = new ArrayList<>();
        if (hasDirectory && !directory.exists()) {
            directory.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeInvalidFileException();
            }
        }
        Scanner s = null;
        try {
            s = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new DukeInvalidFileException();
        }
        assert file.exists() : "File at " + filePath + " should exist at this point.";
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] parts = line.split("\\|");

            //removes leading and trailing spaces
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].trim();
            }

            boolean isDone = false;

            if (parts[1].equals("1")) {
                isDone = true;
            }

            if (parts[0].equals("T")) {
                assert parts.length == 3 : "There should be 3 symbols: " +
                        "class signifier, isDone, and description.";
                ToDo todo = new ToDo(parts[2]);
                if (isDone) {
                    todo.markDone();
                }
                contents.add(todo);
            } else if (parts[0].equals("D")) {
                assert parts.length == 4 : "There should be 4 symbols: " +
                        "class signifier, isDone, description, and deadline.";
                Deadline deadline = new Deadline(parts[2], parts[3]);
                if (isDone) {
                    deadline.markDone();
                }
                contents.add(deadline);
            } else if (parts[0].equals("E")) {
                assert parts.length == 5 : "There should be 5 symbols: " +
                        "class signifier, isDone, description, start, and end.";
                Event event = new Event(parts[2], parts[3], parts[4]);
                if (isDone) {
                    event.markDone();
                }
                contents.add(event);
            } else if (parts[0].equals("F")) {
                assert parts.length == 4 : "There should be 5 symbols: " +
                        "class signifier, isDone, description, and duration";
                FixedDurationTask duration = new FixedDurationTask(parts[2], parts[3]);
                if (isDone) {
                    duration.markDone();
                }
                contents.add(duration);
            }
        }
        return contents;
    }

    /**
     * Writes the specified content to file.
     * Overwrites the current content of the file.
     *
     * @param content The content to be written to file.
     */
    public void save(String content) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(content);
        fw.close();
    }

    /**
     * Writes the specified content to file.
     * Appends the given content to the current content of the file.
     *
     * @param appendedContent The content to be written to file.
     */
    public void saveAppend(String appendedContent) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(appendedContent);
        fw.close();
    }
}
