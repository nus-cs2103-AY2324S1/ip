package duke.storage;

import duke.parse.DateTimeManager;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public class FileCorruptedException extends Exception {
        public FileCorruptedException() {
            super();
        }
    }

    public class FileIOException extends IOException {
        public FileIOException() {
            super();
        }
    }

    private String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<Task> readFromDisk() throws FileCorruptedException, FileIOException {
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
                    task = new ToDo(line[2]);
                    break;
                case "D":
                    String[] split = line[2].split(" /by ", 2);
                    if (split.length != 2) {
                        throw new FileCorruptedException();
                    }
                    try {
                        LocalDateTime dateTime = DateTimeManager.inputToDate(split[1]);
                        task = new Deadline(split[0], dateTime);
                    } catch (DateTimeManager.DateParseException | DateTimeException e) {
                        throw new FileCorruptedException();
                    }
                    break;
                case "E":
                    String[] separateByFrom = line[2].split(" /from ", 2);
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
                        task = new Event(separateByFrom[0], startTime, endTime);
                    } catch (DateTimeManager.DateParseException | DateTimeException e) {
                        throw new FileCorruptedException();
                    }
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
                throw new FileIOException();
            }
        }
    }

    public void saveData(String data) throws FileIOException {
        try {
            FileWriter writer = new FileWriter(this.fileName);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            throw new FileIOException();
        }
    }
}
