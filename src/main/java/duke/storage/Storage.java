package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.parse.DateTimeManager;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Handles access to the storage, both read and write.
 */
public class Storage {
    /**
     * Thrown when the file is found to be corrupted, and data cannot be read properly.
     */
    public class FileCorruptedException extends Exception {
        public FileCorruptedException() {
            super();
        }
    }

    /**
     * Thrown where there is an IO error.
     */
    public class FileIoException extends IOException {
        public FileIoException() {
            super();
        }
    }

    private String fileName;

    /**
     * Instantiates a storage that monitors a file with the given file name.
     * @param fileName The file name to monitor.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Reads data from disk and return a list of task recorded.
     * @return The list of task in order.
     * @throws FileCorruptedException When file is corrupted.
     * @throws FileIoException When there is an IO error.
     */
    public ArrayList<Task> readFromDisk() throws FileCorruptedException, FileIoException {
        File f = new File(this.fileName);
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNext()) {
                String[] line = fileScanner.nextLine().split(" ", 3);
                if (line.length == 0 && !fileScanner.hasNext()) {
                    break;
                }
                if (line.length != 3) {
                    throw new FileCorruptedException();
                }
                Task task;
                switch (line[0]) {
                case "T":
                    task = this.dataToTodo(line[2]);
                    break;
                case "D":
                    task = this.dataToDeadline(line[2]);
                    break;
                case "E":
                    task = this.dataToEvent(line[2]);
                    break;
                default:
                    throw new FileCorruptedException();
                }
                if (line[1].equals("1")) {
                    task.markAsDone();
                } else if (line[1].equals("0")) {
                    task.markAsNotDone();
                } else {
                    throw new FileCorruptedException();
                }
                taskList.add(task);
            }
            fileScanner.close();
            return taskList;
        } catch (FileNotFoundException fileError) {
            try {
                f.createNewFile();
                return new ArrayList<>();
            } catch (IOException ioError) {
                throw new FileIoException();
            }
        }
    }

    /**
     * Save data to disk
     * @param data The data to save.
     * @throws FileIoException When there is an IO error.
     */
    public void saveData(String data) throws FileIoException {
        try {
            FileWriter writer = new FileWriter(this.fileName);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            throw new FileIoException();
        }
    }

    private ToDo dataToTodo(String data) {
        return new ToDo(data);
    }

    private Deadline dataToDeadline(String data) throws FileCorruptedException {
        String[] split = data.split(" /by ", 2);
        if (split.length != 2) {
            throw new FileCorruptedException();
        }
        try {
            LocalDateTime dateTime = DateTimeManager.inputToDate(split[1]);
            return new Deadline(split[0], dateTime);
        } catch (DateTimeManager.DateParseException | DateTimeException e) {
            throw new FileCorruptedException();
        }
    }

    private Event dataToEvent(String data) throws FileCorruptedException {
        String[] separateByFrom = data.split(" /from ", 2);
        if (separateByFrom.length != 2) {
            throw new FileCorruptedException();
        }
        String[] separateByTo = separateByFrom[1].split(" /to ", 2);
        if (separateByTo.length != 2) {
            throw new FileCorruptedException();
        }
        try {
            LocalDateTime startTime = DateTimeManager.inputToDate(separateByTo[0]);
            LocalDateTime endTime = DateTimeManager.inputToDate(separateByTo[1]);
            return new Event(separateByFrom[0], startTime, endTime);
        } catch (DateTimeManager.DateParseException | DateTimeException e) {
            throw new FileCorruptedException();
        }
    }
}
