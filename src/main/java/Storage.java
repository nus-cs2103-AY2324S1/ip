import tasks.Task;
import tasks.ToDo;
import tasks.Event;
import tasks.Deadline;

import exceptions.StorageException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.format.DateTimeFormatter;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveData(TaskManager taskManager) throws StorageException {
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Task task: taskManager.getList()) {
                bw.append(task.toString());
                bw.append("\n");
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new StorageException("Couldn't save data");
        }
    }

    public TaskManager loadData() throws StorageException {
        File savedFile = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(savedFile);
            while (fileReader.hasNextLine()) {
                tasks.add(readLine(fileReader.nextLine()));
            }
            fileReader.close();
            TaskManager taskmanager = new TaskManager(tasks);
            return taskmanager;
        } catch (FileNotFoundException e) {
            createFile();
            return new TaskManager();
        }
    }

    // basically the format will be something like this:
    // [T][X] do homework
    // [D][ ] submit assignment (by: Friday)
    // [E][ ] attend lecture (from: Wednesday to: Thursday)
    private Task readLine(String line) throws StorageException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        line = line.trim();
        Pattern todoPattern = Pattern.compile("\\[T\\]\\[(.)\\] (.*)");
        Pattern deadlinePattern = Pattern.compile("\\[D\\]\\[(.)\\] (.*) \\(by: (.*)\\)");
        Pattern eventPattern = Pattern.compile("\\[E\\]\\[(.)\\] (.*) \\(from: (.*) to: (.*)\\)");
        Matcher matcher;

        matcher = todoPattern.matcher(line);
        if (matcher.matches()) {
            boolean isDone = matcher.group(1).equals("X");
            String taskName = matcher.group(2).trim();
            ToDo todo = new ToDo(taskName, isDone);
            return todo;
        }

        matcher = deadlinePattern.matcher(line);
        if (matcher.matches()) {
            boolean isDone = matcher.group(1).equals("X");
            String taskName = matcher.group(2).trim();
            String dueString = matcher.group(3).trim();
            LocalDateTime dueTime = LocalDateTime.parse(dueString, formatter);
            Deadline deadline = new Deadline(taskName, isDone, dueTime);
            return deadline;
        }

        matcher = eventPattern.matcher(line);
        if (matcher.matches()) {
            boolean isDone = matcher.group(1).equals("X");
            String taskName = matcher.group(2).trim();
            String fromString = matcher.group(3).trim();
            String toString = matcher.group(4).trim();
            LocalDateTime fromTime = LocalDateTime.parse(fromString, formatter);
            LocalDateTime toTime = LocalDateTime.parse(toString, formatter);
            return new Event(taskName, isDone, fromTime, toTime);
        }

        throw new StorageException("There was an issue reading your data");
    }

    public void createFile() throws StorageException {
        File file = new File(this.filePath);
        File rootDirectory = new File(file.getParent());
        try {
            rootDirectory.mkdir();
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Unable to create a database");
        }
    }

}
