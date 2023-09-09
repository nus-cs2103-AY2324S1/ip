package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Handles reading and writing of the data file.
 */
public class Storage {
    private final File file;

    /**
     * Constructor for storage. Makes the prerequisite folder and file if necessary.
     * @param filePath Path to the folder.
     */
    public Storage(String filePath) {
        File diskValues = new File(filePath + "/duke.txt");
        this.file = diskValues;
        try {
            File folder = new File(filePath);
            if (!folder.exists()) {
                if (!folder.mkdir()) {
                    throw new IOException();
                }
            }
            if (!diskValues.exists()) {
                if (!diskValues.createNewFile()) {
                    throw new IOException();
                }
            }
        } catch (IOException e) {
            System.out.println("IOException in reading files: " + e.getMessage());
        }
    }

    /**
     * Parses one line of the task and returns the associated task.
     * @param oneTask one line from the data file.
     * @return the task represented by the line from the data file.
     * @throws DukeException if the data file's input does not match writing.
     */
    private Task parseOneLine(String oneTask) throws DukeException {
        Task theTask;
        String taskName;
        char taskType = oneTask.charAt(1);
        String[] splitTask = oneTask.split(" ");
        if (oneTask.charAt(4) == 'X') {
            taskName = splitTask[1];
        } else {
            taskName = splitTask[2];
        }
        if (taskType == 'T') {
            theTask = new Todo(taskName);
        } else if (taskType == 'D') {
            String deadline = parseDeadlineTask(splitTask);
            theTask = new Deadline(taskName, deadline);
        } else if (taskType == 'E') {
            String[] receivedInfo = parseEventTask(splitTask);
            String from = receivedInfo[0];
            String to = receivedInfo[1];
            theTask = new Event(taskName, from, to);
        } else {
            throw new DukeException("Input file corrupted.");
        }
        if (oneTask.charAt(4) == 'X') {
            theTask.completeTask();
        }
        return theTask;
    }

    private String parseDeadlineTask(String[] splitTask) {
        StringBuilder deadline = new StringBuilder();
        String mode = "none";
        for (String command : splitTask) {
            if (Objects.equals(command, "(by:")) {
                mode = "by";
                continue;
            }
            if (Objects.equals(mode, "by")) {
                if (!deadline.isEmpty()) {
                    deadline.append(" ");
                }
                deadline.append(command);
            }
        }
        if (!deadline.isEmpty()) {
            deadline.deleteCharAt(deadline.length() - 1); // Remove last ).
        }
        return deadline.toString();
    }
    private String[] parseEventTask(String[] splitTask) {
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();
        String mode = "none";
        for (String command : splitTask) {
            if (Objects.equals(command, "(from:")) {
                mode = "from";
                continue;
            }
            if (Objects.equals(command, "to:")) {
                mode = "to";
                continue;
            }
            if (Objects.equals(mode, "from")) {
                if (!from.isEmpty()) {
                    from.append(" ");
                }
                from.append(command);
            } else if (Objects.equals(mode, "to")) {
                if (!to.isEmpty()) {
                    to.append(" ");
                }
                to.append(command);
            }
        }
        if (!to.isEmpty()) {
            to.deleteCharAt(to.length() - 1); // Remove last ).
        }
        return new String[]{from.toString(), to.toString()};
    }
    /**
     * Prepares a list of tasks from a data file.
     * @return The tasks represented by the line from the data file.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>(100);
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String oneTask = s.nextLine();
                tasks.add(parseOneLine(oneTask));
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException in reading files: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Writes tasks to the data files.
     * @param tasks Tasks to write to the data file.
     */
    public static void writeToDisk(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt");
            for (Task task : tasks) {
                fw.write(task.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("IOException in writing to file: " + e.getMessage());
        }
    }
}


