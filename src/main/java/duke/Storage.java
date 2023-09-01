package duke;

import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Event;
import duke.tasks.Deadline;

import duke.exceptions.StorageException;

import java.util.ArrayList;
import java.io.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveData(ArrayList<Task> tasks) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Task task: tasks) {
                bw.append(task.toString());
                bw.append("\n");
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("An IO exception Occurred");
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
            String dueDate = matcher.group(3).trim();
            Deadline deadline = new Deadline(taskName, isDone, dueDate);
            return deadline;
        }

        matcher = eventPattern.matcher(line);
        if (matcher.matches()) {
            boolean isDone = matcher.group(1).equals("X");
            String taskName = matcher.group(2).trim();
            String from = matcher.group(3).trim();
            String to = matcher.group(4).trim();
            return new Event(taskName, isDone, from, to);
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
